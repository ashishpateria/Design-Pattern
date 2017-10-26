CS542 Design Patterns
Spring 2017
PROJECT 3 README FILE

Due Date: Sunday,April 2, 2017
Submission Sunday,April 2, 2017
Author: Ashish Pateria
Email-id:apateri1@binghamton.edu

Project Detail:
In this project we have to implement spreadsheet updates, where if one cell is updated all the cell which are dependent on it are updated 
automatically.We have implemented Observer pattern,where each cell has been defined as both observer and Subject.
I have also detected cycles between the cells and a meaningful message has been printed if there is cycle between the cells.

Debug levels:

DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
DEBUG_VALUE=3 [Print to stdout everytime a cell value is updated]
DEBUG_VALUE=2 [Print to stdout everytime a cell is created and added to the CellList]
DEBUG_VALUE=1 [Print to stdout the lines which created the cycles]
DEBUG_VALUE=0 [No output should be printed from the application, except the line The sum of all cell values is:X ]

Percentage Completion:
I believe I have 100% completed.

BUGS:
None

FILES:
Included with this project are 3 packages (11 files):
1. spreadsheetUpdates.driver
Driver.java, the file which has the main method and is responsible for calling the spreadsheet structure.

2. spreadsheetUpdates.observer
Cell.java, file storing each cells info
Observer.java, Observer interface
Subject.java,Subject interface
SpreadsheetExample.java, class responsible for doing all the cell updates

3.spreadsheetUpdates.util
FileProcessor.java
Logger.java

README
build.xml

RUN:
ant -buildfile src/build.xml clean
ant -buildfile src/build.xml all
ant -buildfile src/build.xml run -Darg0=input1.txt -Darg1=output.txt -Darg3=0



ACADEMIC HONESTY:
"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plaigarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.
-ASHISH PATERIA
Sunday, April 2, 2017

DATASTRUCTURE USED:
I have used ArrayList as dataStructure to store all the cells value,because we have mostly
one to many relations in the spreadsheet,which I think can be easily be maintained using
ArrayList as per my algorithm for cell updates.

CITATIONS:
N/A

