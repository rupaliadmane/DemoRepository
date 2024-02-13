@createDBConnection
Feature: Add connection feature in DBeaver

#  Background: Launch DBeaver
#   Given DBeaver is launched


  Scenario: [1] Verify new connection is created successfully using new button from toolbar
    When User clicks on new database connection from toolbar
     And User selects "SQL Server" database
    And User enters database details and clicks Finish
    |Host|localhost|
    |Port|50000    |
    |Database/Schema| testdb|
    |Username| testUser|
    |Password| testpassword|
    Then Verify the database connection "testdb" is created on Database Navigator

  Scenario: [2] Verify new connection is created successfully using ctrl+N button shortcut
    When User selects create connection with keyboard button
    And User selects "Oracle" database
    And User enters database details and clicks Finish
      |Host|localhost1|
      |Port|50001|
      |Database| newdb2|
      |Username| testUser|
      |Password| testpassword|
    Then Verify the database connection "newdb2" is created on Database Navigator

    
    Scenario: [3] Delete database and verify deleted successfully
      When User selects "newdb2" database on database navigator
      And User hits delete button
      Then Verify the confirmation dialog appears
      When User clicks on Yes on Delete object dialogue box
      Then Verify the database connection "newdb2" is not present on Database Navigator