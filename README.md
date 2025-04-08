Titanium Test Automation Framework
==================================
This project is a template for Titanium's test automation framework, which provides a structured and standard way of creating automated test scripts for GUI and API level tests across Titanium projects.Tools & libraries
=================
The test automation framework is comprised of the following tools and libraries:

*Cucumber-JVM:- BDD Framework  
*Custom Page Object Pattern and utility functions  
*Selenium WebDriver : - Browser automation framework  
*JAVA: - Programming language  
*TestNg: - TestNg Java testing framework  
*Maven: - Build tool  
*Jenkins: - Continuous Integration  
*Lombok: - Java utility API  
*PicoContainer: - Dependency Injection    
*Git (optional): - Version Control  
*GitHub (optional): - Git repository hosted server  
*Intellij Or Eclipse: - Integrated Development Environment  
*Hamcrest: - Matchers  
*Loggers: - Simple Logging Facade for Java  
*Joda-Time: - Java Date time API  
*SonarQube (optional): - Code Quality and Code Coverage  
*DbUtils Mysql (optional): - Java Database utility API  
*Rest-Assured (optional): - Restful API frameworkContact information
===================
Overall Test & Test management support
------------------------------------------
Souvik Dutta
------------
Head of Testing  
souviktechnoindia@gmail.com  

Test Automation framework support
------------------------------------------
Souvik Dutta
------------
Test Automation Architect  
souviktechnoindia@gmail.comConfigure Ubuntu / Windows and setup: -   
*Java 7  
*Git  
*Mavengit clone https://github.com/titaniumtest/titaniumtest.gitFramework Setup steps
============================
URL, Browser Configuration, Test Suites to Run
Open `pom.xml` 
Scroll to Profile section: - Choose desired profile e.g "dev" for running locally

            <!-- Development environment @ my local machine -->
                    <profile>
                        <id>dev</id>
                        <activation>
                            <activeByDefault>true</activeByDefault>
                        </activation>
                        <properties>
                            <!-- Application under test-->
                            <site.url>http://www.example.com/</site.url>
                            <!-- Service under test-->
                            <api.url>http://www.exampleapi.com</api.url>
                            <!-- AUT has default desired port-->
                            <site.port></site.port>
                            <!-- AUT has default base path-->
                            <site.basepath></site.basepath>
                            <!--platform to run e.g linux64, mac32, win32, win64-->
                            <platform>linux64</platform>
                            <!--Desired browser to run e.g firefox,chrome,iexplore -->
                            <browser>firefox</browser>
                            <!--To Run parallel Test suite specify the type of Run Files which can be run in parallel -->
                            <testToRun>**/*ATSuite.class</testToRun>