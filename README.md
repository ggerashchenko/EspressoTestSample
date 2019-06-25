# Description
A simple native Android application that shows a city on a map. It provides a list of cities which you can search.

# Acceptence Criteria
* The cities are listed in ascending alphabetical order
* The cities list is scrollable
* The user of the application can search for specific city(ies) 
* The user of the application can select a city to be displayed on the map
* The user of the application can interact with the map (i.e zoom in/out, move to the left/right)

# Create espresso tests task:
Create a series of manual test cases that cover the search functionality
Automate (at least one) of the previously created test cases using Espresso

# Manual test cases for testing Search functionality:
* TC: Verify initial state
1. Start an application

Expected result
Page title is present
Search field is empty
All cities are present

* TC: Verify search by Character
1. Type upercase and lowercase K in search field
2. Type numbers and different symbols in search field

Expected result:
1. Cities started on K are filtered in the list
2. List of cities should be empty

* TC: Verify search by country code
1. Type country code

Expected result:
1. List of cities should be empty

* TC: Verify search by full name is working
1. Type Amsterdam in search field
Expected result
All cities with the name Amsterdam filtered in the list iof cities. "Amsterdam, NL", "Amsterdam, US", "Amsterdam, US", "    Amsterdam-Zuidoost, NL" are present in the list

* TC: Verify city is selectable
1. Type Amsterdam in search field
2. Select "Amsterdam, NL"

Expected result
Opened a new screen with the Amsterdam on the map

* TC: Verify back button
1. Type Amsterdam in search field
2. Select "Amsterdam, NL"
3. Tap back button

Expected result
All cities with the name Amsterdam filtered in the list iof cities. "Amsterdam, NL", "Amsterdam, US", "Amsterdam, US", "Amsterdam-Zuidoost, NL" are present in the list

# How to get a report:
 execute ./gradlew connectedAndroidTest
 Generated test report HTML and XML report will be stored in path_to_your_project/module_name/build/outputs/androidTest-results/connected/ directory.

# How to improve report:
It is possible to add allure reporting which will allow to get report filled with different data as logs, screenshots, history of success and failed test cases.
