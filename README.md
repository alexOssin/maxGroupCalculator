# SDET Coding Test
Alex Ossin
alex.ossin@gmail.com

## Problem Statement
Implement a calculator that given a specific input (described below) outputs the maximum number of units (not Including CCUs) that are connected ,
i.e. located within 1 metre from each other or via a CCU unit.


## Maximum group calculator App (MaxGroupCalculator)

maxGroupCalculator is a simple java application that calculates the maximum number of units that can communicate a message with each other.
The following will start the app
```
java -jar  maxGroupCalculator-1.0-SNAPSHOT.jar path/to/file/fileName.txt
```
NOTE: The file may have been named maxGroupCalculator-1.0-SNAPSHOT.jaz to circumvent email scanners - be sure to rename it to maxGroupCalculator-1.0-SNAPSHOT.jar

The application must be started with one argument — a path to the local file.
The project itself can be found under the maxGroupCalculator directory.
The app was developed and tested on MAC OS and is Java 17+ compatible .


## Objectives derived from the task description .
1. A Node name (not a CCU) should not exceed 20 characters and can be anything .
2. A Central Communication Unit (CCU) starts with "CCU" upper case characters followed by numeric .
3. A line should start with a unit name .
4. Empty lines are not allowed .



NOTE : Assuming  an input file contains a valid representation of all connected units  
no restrictions on groups were implemented .
Please see the list of errors that would be printed to a standard output addressing the mentioned 
above issues accordingly

    UNIT_NAME_EXCEEDS_MAX_LENGTH,
    EMPTY_FIRST_FIELD_IS_NOT_ALLOWED,
    EMPTY_LINES_ARE_NOT_ALLOWED,

## Generation of the communication groups.

The algorithm assigns a unit (CCU or Node) to a specific 
communication group if they are connected to each other via a CCU or located withing 1 meter range ,
using the following definition :
```
for all a, b, c ∈ X, if a R b and b R c, then a R c
//where a,b,c represent a unit (node or CCU) and X represents a group
```
Please see the generateConnectedGroups method under the GroupCalculator class for more details.


## Notes / General / My thoughts 
1. My main goal in this task was to deliver a well-structured code that can be easily refactored or extended.
2. There is no mention of time and space complexity in the solution , as I don't have much of a details about the 
   scalability of a task , e.g. how large a connected group can be , how large are the input files , how fast a 
   group should be generated in real time etc' .    
3. The implementation of the solution + the testing  took me 3 after work evenings (more than 3-4 hrs , as mentioned in the task)
4. For the simplicity matters InputFileReader skips empty chars and whitespaces in between commas in each line .
5. I've probably forgotten to mention some more points  :)

Would appreciate hearing your thoughts and ideas .
Thank you for your time .
Kind Regards,
Alex




  







