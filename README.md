![Salmon](/salmonlogo.png)
================================
Salmon Test Automation Framework
================================
This project is a template for Salmon's test automation framework, which provides structured and standard way of 
creating automated test scripts for GUI and API level tests across salmon projects  

The framework incorporates design principle of BDD (Behaviour driven development) which promotes
 writing acceptance tests by describing behaviour of application under test from
 the perspective of its stakeholders. 
 Having test written in Natural language helps the Project Team 
 (Product Owners, Business Analysts, Development and QA team) to understand and track the requirements
 
Supports Custom Page Object model which represents the screens of AUT as a series of objects and 
encapsulates the fields represented by a page which ultimately avoids duplication and improves code maintainability and readability.
    


Tools & libraries
=================
The test automation framework is comprised of following tools and libraries  
*Cucumber-JVM  
*Custom Page Object Pattern and utility functions    
*Selenium WebDriver  
*JAVA  
*Junit4  
*Maven  
*Jenkins
*Lombok  
*PicoContainer  
*Git (optional)  
*Github (optional)  
*Intellij Or Eclipse  
*Hamcrest  
*Loggers  
*Sonar (optional)  
*DbUtils Mysql (optional)  
*Rest-Assured (optional)    
  

Contact information
===================
Overall Test & Test management support
------------------------------------------
Nick Gee
--------
Head of Testing  
ngee@salmon.com  


Test Automation framework support
------------------------------------------
Gaurav Karvir
--------------
Test Automation Architect  
gkarvir@salmon.com  



Machine Configuration
====================
Configure Ubuntu / Windows and setup: -   
*Java 7  
*Git  
*Maven  


Get the latest Source Code
===========================
Open Terminal or command line
cd to the desired folder where the test automation source code needs to be checkout

Run command
git clone https://github.com/salmontest/salmontest.git

This will download the latest template source code

IDE Configuration
==================
Intellij plugins  

Configure and Install Following Plugins  
File >> Setting >> Plugins >> Browser Repositories>

*Cucumber for Java
*Gherkin
*Junit
*lombok
*Git Integration
*GitHub
*Maven Integration
*SonarQube (optionsl)

Import Project
--------------
File>Import Project>
Browser to SalmonAutomationFramework


Framework Setup steps
============================
URL,  Browser Configuration, Test Suites to Run
Open "pom.xml" 
Scroll to Profile section : - Choose desired profile e.g "dev" for running locally

            <profile>
                           <id>dev</id>
                        <properties>
                            <!-- Application under test-->
                            <site.url>http://www.sysstg.lloydspharmacy.com/</site.url>
                            <!-- Service under test-->
                            <api.url>http://www.dulux.com.sg</api.url>
                            <!-- AUT has default desired port-->
                            <site.port></site.port>
                            <!-- AUT has default base path-->
                            <site.basepath></site.basepath>
                            <!--Desired browser to run -->
                            <browser>firefox</browser>
                            <!--Location of Chrome Driver -->
                            <webdriver.chrome.driver>tools/chromedriver/linux64/chromedriver</webdriver.chrome.driver>
                            <!--To Run parallel Test suite specify the type of Run Files which can be run in parallel -->
                            <testToRun>**/*AT.class</testToRun>
                        </properties>
            </profile>


Compile Build or Run Tests
==========================

Command Line

cd to root ot salmonAutomationFramework project directory

To clean and compile the build
-----------------------------
mvn clean install

To run all tests parallel
------------------------
mvn clean install -P dev

To run a single test with tags
------------------------

mvn clean install -Dcucumber.options="--tags @gui --tags ~@api" -P single

** Note "~" before tag means this specific tag will not run


Report
======

Local report
-------------
A report will be generated at /target/cucumber-report/index.html

Jenkins report
--------------
The report will be available as part of configured Jenkins test build  
**Cucumber plugin for Jenkins needs to be installed





Getting Started
===========================


Feature Files
-------------------------------------------------------------------
These files contains the acceptance criteria which are written in Gherkin Language and contains various scenarios.  
The feature files are tagged with "@tagname" to group common feature files 

File Extension:  *.feature    
Location: "/home/dev/src/salmonAutomationFramework/src/test/resources/features"      
Directory:  Separate directories for GUI and API tests, Group common features files in a 
single directory    
File Conventions:Meaning full name "WebRegister.feature"
Example:   
@gui
Feature: REGISTER:- As a new customer of Lloydspharmacy I would like to register 
Scenario:Perform a New registration for a customer
    Given I navigate to the Lloydspharmacy "HOME" page


Page Objects
-------------------------------------------------------------------
PageObjects are used to store the WebElements for a Web Page.
A good practice is to have a separate class for every single WebPage.
To avoid duplication for multiple pages which have common web page elements a Parent class can be created 
and the child class can then inherit.  
Every Page  class extends "PageObject.class" to make use of the WebDriver Object and utility functions.  
In case of Parent and Child Class, Parent class extends PageObject class and child class extends Parent class      
   
Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test/pageobjects
Directory structure: Group common Page Objects classes in a single directory e.g Login Functionality Classes in Login Directory      
File Conventions:Every Class file ends with Page.class (Homepage.class)  

Example:   

public class HomeSamplePage extends PageObject {

    private By headerSignInLink = By.cssSelector("#headerSignInLink a");

    public void clickSignInLink() {
        waitForExpectedElement(headerSignInLink).click();
    }
}


Step Definitions
--------------------------------------------------------------------
Every steps defined in Feature file needs to be implemented in Step Definitions Class

Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test/step_definitions 
Directory structure: Separate directories for GUI and API tests, Group common step definition files in a 
                     single directory    
File Conventions:Every Class file ends with Steps.class (LoginSteps.class)  

Example:  

public class HomePageSteps {

   private HomeSamplePage homePage =  new HomeSamplePage();

    @And("^i click on Sign In on the Home Page$")
    public void i_click_on_Sign_In_on__the_Home_Page() throws Throwable {
        homePage.clickSignInLink();
    }
}

Run Test Suite
--------------------------------------------------------------------
Test Suites are used to run a group of Tests which are tagged and represented in form of Feature files & Scenarios

Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test
File Conventions:Every Class file ends with Suite.class (RunWebAT.class)  


@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes", tags = {"@gui,@api}, format = {"pretty","html:target/cucumber-report/runwebat","json:target/cucumber-report/runwebat/cucumber.json"})
public class RunWebATSuite {
}

Where: -  
features: represent the location of feature files from the compiled build  
tags:  multiple tags can be specified by comma separated denotation, if a specific tag needs to be excluded then this 
        can be specified by "~" . e.g "~@api" feature files tagged with "~api" will not be run as a part of Test Suite.  
format: html and json reports are created. if a TesSuite is renamed then change the reporting directory name for both reports   
