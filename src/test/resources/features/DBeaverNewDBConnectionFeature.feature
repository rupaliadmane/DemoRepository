Feature: Add connection feature in DBeaver

  Background: Launch DBeaver
   Given DBeaver is launched

    @createDBConnection
  Scenario: Verify new connection is created successfully
    When User selects create connection on Database Navigator
     And User selects "SQL Server" database
    And User enters database details and clicks Enter
    |Host|localhost|
    |Port|50000    |
    |Database/Schema| testdb|
    |Username| testUser|
    |Password| testpassword|
    Then Verify the database connection "testdb" is created on Database Navigator
      And Close Dbeaver application
