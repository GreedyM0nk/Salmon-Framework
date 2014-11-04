![Salmon](/salmonlogo.png)
================================
Salmon Test Automation Framework
================================
This project is a template for Salmon's test automation framework 


Contact information
===================
For any test automation framework queries please contact: -
Gaurav Karvir
Test Automation Architect
gkarvir@salmon.com

For overall Testing queries please contact: -
Nick Gee
Head of Testing
ngee@salmon.com

To run, you will need to install maven, then:
mvn clean install -P profile


Tools & libraries
=================
The test automation framework is comprised of following tools and libraries
*Cucumber-JVM  
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


Machine Configuration
====================
Configure Ubuntu / Windows and setup: - 
*Java 7  
*Git  
*Maven  




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
*SonarQube

Import Project
--------------
File>Import Project>
Browser to SalmonAutomationFramework


AUT Setup Steps
============================
URL and Browser Configuration
Open "pom.xml" 
Scroll to Profile section : - Choose desired profile e.g for running locally

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
Report: -
====================================
A report will be generated at /target/cucumber-report/index.html
