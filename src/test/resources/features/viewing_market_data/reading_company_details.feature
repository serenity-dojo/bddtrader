Feature: Reading company data

  In order to know who I am investing in
  As a trader
  I want to be informed of relevant news about shares I am interested in

  @api
  Scenario: Viewing news about a particular share
    Given Tim is interested in Apple
    When Tim views the news about AAPL
    Then Tim should only see articles related to AAPL

