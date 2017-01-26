/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.tools.intellij.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.services.source.model.ListReposResponse;
import com.google.api.services.source.model.Repo;
import com.google.cloud.tools.intellij.login.CredentialedUser;
import com.google.cloud.tools.intellij.resources.RepositorySelector.ProjectNotSelectedPanel;
import com.google.cloud.tools.intellij.resources.RepositorySelector.RepositoryPanel;
import com.google.cloud.tools.intellij.vcs.CloudRepositoryService;
import com.google.cloud.tools.intellij.vcs.CloudRepositoryService.CloudRepositoryServiceException;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.testFramework.PlatformTestCase;
import com.intellij.ui.awt.RelativePoint;

import org.picocontainer.MutablePicoContainer;

import java.awt.Point;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Tests for {@link RepositorySelector}.
 */
public class RepositorySelectorTest extends PlatformTestCase {

  private CloudRepositoryService repositoryService;

  @Override
  public void setUp() throws Exception {
    super.setUp();

    MutablePicoContainer applicationContainer = (MutablePicoContainer)
        ApplicationManager.getApplication().getPicoContainer();

    repositoryService = mock(CloudRepositoryService.class);

    applicationContainer.unregisterComponent(CloudRepositoryService.class.getName());
    applicationContainer.registerComponentInstance(
        CloudRepositoryService.class.getName(), repositoryService);
  }

  public void testShowsMissingProjectPanel_WhenProjectIsMissing() {
    JPanel panel = getMissingProjectPanel();

    // Shows the project not selected panel
    assertInstanceOf(panel, ProjectNotSelectedPanel.class);
  }

  public void testShowsNoRepositoriesMessage_WhenNoCloudReposFound()
      throws IOException, GeneralSecurityException {
    JPanel panel = getEmptyRepositoriesPanel();

    // Shows a 'no repositories' message
    assertInstanceOf(getPanelObject(panel), ResourceEmptyModelItem.class);
  }

  public void testListsRepositories() throws IOException, GeneralSecurityException {
    JPanel panel = getPopulatedRepositoriesPanel();

    // Contains a repository item
    assertInstanceOf(getPanelObject(panel), RepositoryModelItem.class);
  }

  public void testShowsListError_WhenServiceThrowsException() {
    JPanel panel = getErrorRepositoryPanel();

    // Contains an error item
    assertInstanceOf(getPanelObject(panel), ResourceErrorModelItem.class);
  }

  private JPanel getMissingProjectPanel() {
    RepositorySelector selector = new RepositorySelector(
        null /*cloudProject*/,
        null /*user*/,
        false /*canCreateRepository*/);

    selector.showPopup(new RelativePoint(selector, new Point(0, 0)));

    return selector.getPanel();
  }

  /**
   * Extracts the content of the repository tree
   * e.g. a missing repositories message, or the repositories themseleves (only the first item).
   */
  private Object getPanelObject(JPanel panel) {
    // Shows the repositories panel
    assertInstanceOf(panel, RepositoryPanel.class);

    DefaultTreeModel treeModel = ((RepositoryPanel) panel).getTreeModel();
    Enumeration root = ((DefaultMutableTreeNode) treeModel.getRoot()).children();
    assertTrue(root.hasMoreElements());

    Object repositories = root.nextElement();

    assertInstanceOf(repositories, ProjectRepositoriesModelItem.class);

    Enumeration children = ((ProjectRepositoriesModelItem) repositories).children();
    assertTrue(children.hasMoreElements());

    return children.nextElement();
  }

  private JPanel getEmptyRepositoriesPanel() throws IOException, GeneralSecurityException {
    when(repositoryService.listAsync(any(CredentialedUser.class), anyString()))
        .thenReturn(CompletableFuture.completedFuture(new ListReposResponse()));

    return getPanel();
  }

  private JPanel getPopulatedRepositoriesPanel() throws IOException, GeneralSecurityException {
    ListReposResponse reposResponse = new ListReposResponse();
    reposResponse.setRepos(createRepos());

    when(repositoryService.listAsync(any(CredentialedUser.class), anyString()))
        .thenReturn(CompletableFuture.completedFuture(reposResponse));

    return getPanel();
  }

  private JPanel getErrorRepositoryPanel() {
    when(repositoryService.listAsync(any(CredentialedUser.class), anyString()))
        .thenReturn(CompletableFuture.supplyAsync(() -> {
          throw new CloudRepositoryServiceException();
        }));

    return getPanel();
  }

  private JPanel getPanel() {
    RepositorySelector selector = createInitializedSelector();
    selector.showPopup(new RelativePoint(selector, new Point(0, 0)));

    return selector.getPanel();
  }

  private List<Repo> createRepos() {
    Repo repo1 = new Repo();
    repo1.set("name", "repo1");

    Repo repo2 = new Repo();
    repo2.set("name", "repo2");

    return Arrays.asList(repo1, repo2);
  }

  private RepositorySelector createInitializedSelector() {
    return new RepositorySelector(
        "my-project",
        mock(CredentialedUser.class),
        false /*canCreateRepository*/);
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }
}
