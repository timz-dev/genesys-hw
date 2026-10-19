# genesys-hw

UI (Selenium + TestNG) and API (REST Assured) tests.

## Prerequisites

- JDK 17–21 (Gradle 8.4 wrapper is included, no separate Gradle install needed)
- Google Chrome. Selenium Manager downloads a matching chromedriver automatically.

## Running the tests

Run all tests:

```sh
./gradlew test
```

Run a single test with single class:

```sh
./gradlew test --tests "com.genesys.hw.tests.saucedemo.SauceDemoTest"
./gradlew test --tests "com.genesys.hw.tests.htmleditor.HtmlEditorTest"
./gradlew test --tests "com.genesys.hw.tests.api.UsersApiTest"
```
or

```sh
./gradlew test --tests "SauceDemoTest"
./gradlew test --tests "HtmlEditorTest"
./gradlew test --tests "UsersApiTest"
```

or by method:

```sh
./gradlew test --tests "SauceDemoTest.verify_error_messages_for_mandatory_fields"
```


### Optional settings

- `CHROMEDRIVER_LOCATION` env var: path to a specific chromedriver binary, if you don't want Selenium Manager to download one.
  ```sh
  CHROMEDRIVER_LOCATION=/path/to/chromedriver ./gradlew test
  ```
- `-Dfooter.year=<year>`: expected year in the SauceDemo footer (default `2024`).
  ```sh
  ./gradlew test -Dfooter.year=2025
  ```

## Reports

TestNG/Gradle HTML report: `build/reports/tests/test/index.html`
