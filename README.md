## Argiope Web App
### 1. Getting Started
_The below has not been fully tested yet. I need to do so on a fresh windows VM instance to verify no other environment modification is required._  

##### 1.1 Running Web App
1. Configure Database
   1. TODO: This has not been automated yet (sorry) I need to either:
      1. Implement a database source control tool like Liquibase (Preferred method)
      1. As part of build process, generate SQL scripts to create database and insert test data
      1. Use an in-memory database for development (cons: might get out of sync with the real db)
      1. Use Hibernate to automatically create DB schema if it doesn't exist (cons: risky, could accidentally modify prod DB if precautions are not taken)
1. Run Web App
   1. Method 1: Run Using Eclipse STS
      1. Install [Eclipse Spring Tool Suite (STS)][STS Link]
      1. In the STS 'Spring Boot' view, right click the app > click **(Re)start**
   1. Method 2: Run Using Maven Commandline
      1. From project root dir run command: **mvnw clean spring-boot:run**  
  This should build and start the web app in the current command prompt window. No maven install required (Uses maven wrapper in .mvn directory)
1. Visit localhost:8080 and login using:  
   1. TODO: Add a default username/password when in dev mode and add those credentials to this line

##### 1.2 Running Integration Tests 

_This is currently done through TestNG but this will be handled by Spring Test after E030-SpringTesting._

1. Install TestNG plugin for Eclipse
   * Software Site: http://beust.com/eclipse
1. Run web app as detailed above
1. Open Eclipse STS Run Configurations
1. Run or Debug the **webapp-argiope-TestNG** configuration  
   * This should automatically show up in the run/debug configurations as it is included in the project [here](conf/run-configs/webapp-argiope-TestNG.launch)

### 2. Overview
##### 2.1 Frameworks in Use
* Non-Testing
  * Spring Boot
  * Spring Web
  * Spring Security
  * Spring Embedded TomCat
  * Spring JDBC (SQL Server impl)
     * This will be replaced by Spring Data (Hibernate impl) after E016-SpringData
  * JSP
     * This will be replaced by Spring Thymeleaf after E032-ThymeLeaf
* Testing
  * Selenium
  * TestNG
     * This will be replaced by Spring Test after E030-SpringTesting

##### 2.2 Development Environment
* **IDE**: Eclipse Spring Tool Suite ([STS][STS Link])  
  _Can use any IDE but only STS configuration files should be checked into source control._
* **Servlet Container**: TomCat Embedded (Spring managed)
* **Source Control**: [Git](https://github.com/pbriggs28/Argiope.git)
* **Hosting Environment**: Amazon Web Services
  * **Prod URL**: TODO
  * **QA URL**: TODO
  * **Dev URL**: TODO

##### 2.3 Upcoming Changes
_This is not an all inclusive list, just some of the tickets currently  in the pipeline._

_Note that since I am the only person working on this project, I can keep the ticket descriptions concise. If I eventually introduce others to this project, ticket descriptions will be much more thorough and include test cases and mock ups._

* [E009-Logging]: Add logging to all classes.
* [E016-SpringData]: Convert from Spring JDBC template to Spring Data using Hibernate.
* [E030-SpringTesting]: Convert from TestNG to Spring Test.
* [E032-ThymeLeaf]: Convert from JSP to Thymeleaf (Verify Thymeleaf allows same 'tag' and 'template' reusability patterns)
* [E053-VersionStamping]: Add build version stamping that will be hidden in html but can also be viewed at a specific URL or from web services. Setup integration testing to only test on an accepted list of versions.
* [E084-MoveConfigToResources]: Move everything to be included in build out of the root directory (ex: Config dir, chromedriver.exe) and into the resources directory where it belongs.
* [E090-ExtractSslKey]: Keep the basic development SSL key in the project for dev work but add a check for a key in an external location for the prod servers
* [E063-ExceptionController]: Add a spring exception handler that routes all errors to a specific page. If in dev mode display error instead of routing to specific page.
* [E068-RememberMe]: Add Spring's 'Remember Me' service for login
* [E074-InitializeUserPassword]: Remove password entry from create user and instead require the user to create a password on first login.
* [E077-UpdatePassword]: Add functionality for an authenticated user to change their password. Tutorial: http://www.baeldung.com/updating-your-password/
* [E080-LockUserAccount]: Lock user out after a number of failed password attempts and display a message to the user. Will need to change IP blocking testing to use different accounts so it does not get locked out at a user level when testing a brute force attack.

##### 2.4 Completed Changes
* ~~[E031-SpringBoot]~~: Convert app to use Spring Boot.
* ~~[E069-ipBlocking]~~: Prevent Brute Force attacks by blocking requests at an IP level (not username level) if a set number of failed login attempts are reached.
* ~~[E045-PasswordEncoder]~~: Encrypt passwords using Bcrypt password encoder with unique salts.
* ~~[E067-RequireHttps]~~: All requests to HTTP pages will be redirected to HTTPS

##### 2.5 Known Issues
* [F012-ConstantInterfaceAntipattern]: Implementing an interface (or extending an Abstract class) to 'inherit' constants is an anti-pattern as stated in section 19 of the Effective Java 2nd Edition book. Change abstract constant class to be a concrete class with a private constructor (non-instantiable) and have classes use a static import if they want to reference the constants without prefacing the class name.
* [F015-SeleniumVerboseLogging]: Since changing to Spring Boot, Selenium is logging at a debug level which is about 500 log messages per second. Find out what is causing this and revert it.


[STS Link]: https://spring.io/tools/eclipse