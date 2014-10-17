#Sample test scenario which gives an example of API or Service level testing

@api

Feature: Check Colour details for products

  Scenario: Get Colour details for products
    When I perform GET request for "/en/api/products/colors" endpoint
    Then I get a 200 http status code
    And the colour collections contains colour name
