# Release Engineer Code Challenge | Android

[![Build Status](https://travis-ci.com/asadmansr/android-releng.svg?branch=master)](https://travis-ci.com/asadmansr/android-releng)

test

### App Detail

We have built a simple app that makes an open API call to get list of repositories in GitHub. The app uses single activity with multiple fragments and has a minimalistic UI with a recycler view with CTA. On tapping of an item it shows a detailed view. On back press, the app navigates back to the listview. And most importantly, for code cverage, [JaCoCo](https://github.com/jacoco/jacoco) has been enabled.

Unfortunately the developers have not written any test and have not set the project up for test automation and continuous integration (CI) and delivery. They need your help in building an integrated delivery pipeline.

### Challenge Instructions

Details regarding the full challenge can be found in the [challenge.md](challenge.md)

### Flow

###### Develop ➞ Unit Test ➞ API Test ➞ Android Test ➞ Open Pull Request ➞ Automated Build ➞ Code Violation is posted ➞ Merge ➞ Run Regression
