# Release Engineer Code Challenge | Android

## App Detail
A team of developers built a simple app that makes an open API call to get a list of repositories on GitHub. The applications uses a single activity with multiple fragments and has a minimalistic UI. When the app launches, it displays a list of repositories and when the user taps on a repository, a detailed view is shown. On back press, the app navigates back to the list. Code coverage, [JaCoCo](https://github.com/jacoco/jacoco), has been enabled for this project.

Unfortunately, the team has not set up the project for test automation or continuous integration and delivery (CI/CD). As a result, bugs have been introduced into the codebase. They need your help in identifying the bugs, providing the fix and building an integrated delivery pipeline to ensure the application is stable in the future.

</br>

## Requirements
- The app must show a list of repositories on launch.
- When a user taps on a repository, a detailed view of the repository should be shown with the correct values.
- The application must deliver a good user experience both in portrait and landscape mode.

</br>

## Instructions
Here's their wishlist:

### Development
- Identify and fix at minimum of two bugs in the codebase.

### Testing
- Write JUnit and Android UI tests in Kotlin using Mockito, Espresso, [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) or any other similar libraries.
- The test suite should have at least two JUnit tests.
- The test suite should have at least one Automated UI Test that makes mock API call, return data via Mockito or MockWebserver.
- The test suite should have at least two Android Unit Tests (JUnit tests that uses mocked android components).
- Include few skipped/ignored tests.
- TIP: You may find trouble in launching an emulator on Travis or GitLab, alternatively use [Firebase Test Lab](https://firebase.google.com/docs/test-lab/android/command-line)

### Additional Tips
- You can use dependency injection tools like dagger.
- Feel free to refactor the code as per your needs.
- Code should follow standard naming convention and good practices.
- You are open to use any tools available or libraries such as fastlane, danger, buck, etc.

### Static Analysis
- Include sonarqube as a gradle plugin.
- When `./gradlew sonarqube` is executed, the task compile and run all the tests (should target debug flavour only) and publish the report to [sonarsource](https://www.sonarsource.com/), if you have private sonarqube repo, push to the same and provide access for review.
- Alternatives like [codeclimate](https://codeclimate.com/)/[codefactor](https://www.codefactor.io/) can be used.

### Project
- Your project should be live on Github/Gitlab/Bitbucket and be set up for continuous integration - running builds and automated test on every pull request. It should be easy for other developers to add to the code base.
- Ensure build is successful, with the above conditions when tested.

</br>

Showcase your learning and simply impress us. Have fun!