# Titanium Test Automation Framework

This project is a template for Titanium's test automation framework, providing a structured and standard way to create automated test scripts for GUI and API level tests across Titanium projects.

---

## Tools and Libraries Used

- **Cucumber-JVM**: BDD Framework
- **Custom Page Object Pattern** and utility functions
- **Selenium WebDriver**: Browser automation framework
- **Java**: Programming language
- **TestNG**: Java testing framework
- **Maven**: Build tool
- **Jenkins**: Continuous Integration
- **Lombok**: Java utility API
- **PicoContainer**: Dependency Injection
- **Git** (optional): Version Control
- **GitHub** (optional): Git repository hosted server
- **IntelliJ or Eclipse**: Integrated Development Environment
- **Hamcrest**: Matchers
- **SLF4J**: Simple Logging Facade for Java
- **Joda-Time**: Java Date-Time API
- **SonarQube** (optional): Code quality and coverage
- **DbUtils MySQL** (optional): Java Database utility API
- **Rest-Assured** (optional): RESTful API framework

---

## Contact Information

**Overall Test & Test Management Support**  
Souvik Dutta  
Head of Testing  
[souviktechnoindia@gmail.com](mailto:souviktechnoindia@gmail.com)

**Test Automation Framework Support**  
Souvik Dutta  
Test Automation Architect  
[souviktechnoindia@gmail.com](mailto:souviktechnoindia@gmail.com)

---

## Framework Setup

### Prerequisites

- Java 7+
- Git
- Maven

### Getting Started

1. **Clone the repository:**
   ```sh
   git clone https://github.com/titaniumtest/titaniumtest.git
   ```

2. **Configuration Steps:**
   - Open `pom.xml`.
   - Scroll to the Profile section.
   - Choose the desired profile (e.g., `"dev"` for running locally).

---

## Sample Profile Configuration

```xml
<profile>
    <id>dev</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
        <!-- Application under test -->
        <site.url>http://www.example.com/</site.url>

        <!-- Service under test -->
        <api.url>http://www.exampleapi.com</api.url>

        <!-- AUT has default desired port -->
        <site.port></site.port>

        <!-- AUT has default base path -->
        <site.basepath></site.basepath>

        <!-- Platform to run e.g., linux64, mac32, win32, win64 -->
        <platform>linux64</platform>

        <!-- Desired browser to run e.g., firefox, chrome, iexplore -->
        <browser>firefox</browser>

        <!-- To run parallel test suites -->
        <testToRun>**/*ATSuite.class</testToRun>
    </properties>
</profile>
```

---

## Running Tests

To run tests, use Maven from the project root:

```sh
mvn clean install
```

Or specify a profile:

```sh
mvn clean install -P dev
```

Test results and reports will be available in the `target/surefire-reports` directory.

---

## Additional Notes

- Update the `pom.xml` dependencies and plugins as needed for your environment.
- Refer to the documentation of each tool/library for advanced configuration.

---

