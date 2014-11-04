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
encapsulates the fields represented by a page.    
    


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
*Mysql (optional)  
*Rest-Assured (optional)    
*DbUtils (optional)  


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
URL and Browser Configuration
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


Run
========

Report
=========
A report will be generated at /target/cucumber-report/index.html
