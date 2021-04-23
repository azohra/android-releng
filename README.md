# Release Engineer Code Challenge | Android

## App Detail
A team of developers built a simple app that makes an open API call to get a list of repositories on GitHub. The application uses a single activity with multiple fragments and has a minimalistic UI. When the app launches, it displays a list of repositories, and when the user taps on a repository, a detailed view is shown. On back press, the app navigates back to the list. Code coverage, [JaCoCo](https://github.com/jacoco/jacoco), has been enabled for this project.

Unfortunately, the team has not set up the project for test automation or continuous integration and delivery (CI/CD). As a result, bugs have been introduced into the codebase. They need your help in identifying the bugs, providing the fix and building an integrated delivery pipeline to ensure the application is stable in the future.

</br>

## Requirements
- The app must show a list of repositories on launch.
- When a user taps on a repository, a detailed view of the repository should be shown with the correct values.
- The application must deliver a good user experience both in portrait and landscape mode.

</br>

## Instructions
Here is their wishlist:

### Development
- Identify and fix any bugs in the codebase.

### Testing
- Write JUnit and Android UI tests in Kotlin using Mockito, Espresso, [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) or any other similar libraries.
- The test suite should have at least two JUnit tests.
- The test suite should have at least one Automated UI Test that makes mock API call, return data via Mockito or MockWebserver.
- The test suite should have at least two Android Unit Tests (JUnit tests that use mocked android components).
- TIP: You may find trouble in launching an emulator on Travis or GitLab, alternatively use [Firebase Test Lab](https://firebase.google.com/docs/test-lab/android/command-line)

### Static Analysis
- Enable and/or add a linting analysis tool. 
- Store the test and linting results documentation as artifacts or cached files.

### Additional Tips
- You can use dependency injection tools like a dagger or hilt.
- Feel free to refactor the code as per your needs.
- Code should follow the standard naming convention and good practices.
- Use any tools or libraries available such as Fastlane, danger, buck, etc.

### project
- Your project should live on Github and be set up for continuous integration - running builds and automated tests on every pull request. It should be easy for other developers to add to the code base.
- Ensure build is successful, with the above conditions when tested.

</br>

Showcase your learning and simply impress us. Have fun!