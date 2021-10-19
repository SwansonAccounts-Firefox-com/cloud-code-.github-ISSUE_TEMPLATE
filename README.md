# Cloud Code for IntelliJ 
<img src="cloud_code.png" alt="Cloud Code" width="150" />

Cloud Code for IntelliJ is a plugin that helps facilitate cloud-native development in the JetBrains 
family of IDEs. The plugin adds support for Kubernetes applications, as well as support for various
Google Cloud Platform products.

## Features

* [Kubernetes](https://cloud.google.com/code/docs/intellij/quickstart-k8s) Streamline your Kubernetes development process in the JetBrains family of IDEs.
* [Cloud Run](https://cloud.google.com/code/docs/intellij/quickstart-cloud-run) Iterate on and debug your services locally, deploy them to Cloud Run fully managed or Anthos, and browse your Cloud Run services right from the IDE.
* [Google Cloud Java Client Libraries](https://cloud.google.com/tools/intellij/docs/client-libraries) 
  Add Java client libraries to your project, enable Google Cloud APIs, and create service accounts.
* [Google Cloud Storage](https://cloud.google.com/storage/) 
  Browse your Google Cloud Storage buckets.
* [Google Cloud Source Repositories](https://cloud.google.com/tools/cloud-repositories/) 
  Fully-featured private Git repositories hosted on Google Cloud Platform.
* The [Google Cloud Debugger](https://cloud.google.com/tools/cloud-debugger/) 
  The Cloud Debugger can inspect the state of a Java or Kotlin application running on 
  GCP without stopping or slowing down the application.
* [Google App Engine](https://cloud.google.com/appengine/docs/) deployment via the Cloud SDK.

## Resources
* [Learn More](https://cloud.google.com/code): Learn more about the Cloud Code Project and what it has to offer.
* [Documentation](https://cloud.google.com/code/docs/intellij/): Visit our official documentation to learn more.
* [Talk to us](https://join.slack.com/t/googlecloud-community/shared_invite/zt-erdf4ity-8ZMUQ18DYV~5hkbZ~gCswg): Connect to the Cloud Code development team by joining our #cloud-code Slack channel.
* [Kubernetes Sample Applications](https://github.com/GoogleCloudPlatform/cloud-code-samples): Starter applications for working with Kubernetes; available in Java, Node, Python, and Go.
* [File an Issue](https://github.com/GoogleCloudPlatform/google-cloud-intellij/issues/new): If you discover an issue please file a bug and we will address it. 
* [Request a Feature](https://github.com/GoogleCloudPlatform/google-cloud-intellij/issues/new): If you have any feature requests, please file a request.
* [FAQ/Troubleshooting](https://cloud.google.com/code/docs/intellij/troubleshooting)

## Supported Platforms

The Cloud Code for IntelliJ plugin supports JetBrains IDEs version 2019.3+: 
* [IntelliJ IDEA Ultimate or Community](https://www.jetbrains.com/idea/)
* [PyCharm Professional or Community](https://www.jetbrains.com/pycharm/)
* [WebStorm](https://www.jetbrains.com/webstorm/)
* [GoLand](https://www.jetbrains.com/go/)
* [other JetBrains IDEs](https://www.jetbrains.com/products.html)

[Learn more about the IntelliJ version support policy](https://cloud.google.com/code/docs/intellij/version-support).

For GCP functionality, full support is available for IntelliJ IDEA Ultimate Edition, with limited
support for the other platforms. See this [feature matrix](https://cloud.google.com/code/docs#features)
for more details.

## Installation

You can find our plugin in the Jetbrains plugin repository by going to IntelliJ -> Settings -> Plugins , and search for `Cloud Code` in the 'Marketplace' tab. 

### Insider Builds 

You can access pre-release Cloud Code features by participating in the [Cloud Code Insiders program](https://cloud.google.com/code/docs/intellij/insiders):

1. Open the IDE with the Cloud Code plugin installed
    1. Select `Tools` > `Cloud Code` > `Help / About` > `About Cloud Code...`
    2. Navigate to the `Cloud Code Insiders` section via the table of contents.
    3. Enable the Insider build by clicking the toggle button. Note: after enabling the toggle, a notification will appear prompting you to check for updates - follow this prompt to install the latest Insider build.

## Security Disclosures

Please see our [security disclosure process](SECURITY.md). All [security advisories](https://github.com/GoogleCloudPlatform/cloud-code-intellij/security/advisories) are managed on Github.
