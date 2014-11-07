#This is a sample Feature file with an example of a single scenario.
#Every feature file starts with "Feature:" Keyword which describes what Feature is under test.

#tags are used for running entire Feature, a single Scenario or group of Feature or Scenarios.
#Tags are annotated with "@" symbol

@gui

Feature: REGISTER:- As a new customer of Lloydspharmacy I would like to register so I can : -
  receive advance notice of promotions
  place orders and track their status
  register for Express Repeat Prescription Service



#This is a typical scenario Example. Every Scenario starts with "Scenario:" keyword.
#Scenarios are represented in Given When Then syntax

  Scenario:Perform a New registration for a customer
    Given i navigate to the Lloydspharmacy "HOME" page
    And i click <"SIGN_IN"> on the Home Page
    And i click on Register for New Registration
    When i register a customer on New Registration page
    





   # When I register as a new user
    #Then I am presented with an account summary screen




