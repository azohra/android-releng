# Release Engineer Code Challenge | Android

### App Detail
We have built a simple app that makes an open API call to get list of repositories in GitHub. The app uses single activity with multiple fragments and has a minimalistic UI with a recycler view with CTA. On tapping of an item it shows a detailed view. On back press, the app navigates back to the listview. And most importantly, for code cverage, [JaCoCo](https://github.com/jacoco/jacoco) has been enabled.

Unfortunately the developers have not written any test and have not set the project up for test automation and continuous integration (CI) and delivery. They need your help in building an integrated delivery pipeline.

### Instructions
Here's their wishlist:

- Write jUnit tests and Android UI tests (Java/Kotlin) using Mockito, Espresso, [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) or any other similar libraries.
- The test suite should have at least one Automated UI Test that makes mock api call, return data via mockito or MockWebserver.
- The test suite should have at least two jUnit tests
- The test suite should have at least two Android Unit Tests (jUnit tests that uses mocked android components)
- You can use dependency injection tools like dagger.
- Feel free to refactor the code as per your needs.
- Code should follow standard naming convention and good practices.
- You can add or remove any libraries in app.
- Include few skipped/ignored tests.
- Ensure build is successful, with the above conditions when tested.
- Include sonarqube as a gradle plugin.
- When `./gradlew sonarqube` is run, this should compile and run all tests (should target debug flavour only) and publish the report to [sonarsource](https://www.sonarsource.com/) , if you have private sonarqube repo, push to the same & provide access for review.
- Alternatives like [codeclimate](https://codeclimate.com/)/[codefactor](https://www.codefactor.io/) can be used.
- Your project should be live on Github/Gitlab/Bitbucket and be set up for continuous integration - running builds and automated test on every pull request. It should be easy for other developers to add to the code base.
- You are open to use any tools available or libraries.
- You may find trouble in launching emulator in Travis or GitLab, alternatively use [firebase TestLab CLI](https://firebase.google.com/docs/test-lab/android/command-line)
- You can use fastlane, danger, buck anything you have used.
- Showcase your learning and simply impress us.

### Flow
###### Develop ➞ Unit Test ➞ API Test ➞ Android Test ➞ Open Pull Request ➞ Automated Build ➞ Code Violation is posted ➞ Merge ➞ Run Regression
