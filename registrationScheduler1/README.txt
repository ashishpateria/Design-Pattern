CS542 Design Patterns
Spring 2017
PROJECT 1 README FILE

Due Date: Sunday, February 12, 2017
Submission Date: Sunday, February 12, 2017
Author: Ashish Pateria
Email-id:apateri1@binghamton.edu

Project Detail:
PURPOSE:
In this project, course allocation is done using multi threading. There are 8 courses each having 60 seats available and 80 students are required to give 5 preferences.
5 courses would be allocated to these students most likely according to their preferences.
preference score will be calculated according to the courses they get. 
If a student gets her first choice, the preference score is 6. If a student gets her second choice, the preference score is 7, and so on. 
At the end average preference score of the whole class is calculated.
Upto 3 threads can work on allocation process. Object pool is created for 8 courses which will keep it synchronized.


Debug levels:

DEBUG_VALUE=0 (No output should be printed from the application, except the line "The average preference value is X.Y")
DEBUG_VALUE=1 (Print to stdout when a student entry need to be printed )
DEBUG_VALUE=2 (Print to stdout everytime an entry is added to the Results data structure)
DEBUG_VALUE=3 (Print to stdout everytime a constructor is called) 

Percentage Completion:
I believe I have 100% completed.

BUGS:
None

FILES:
Included with this project are 3 packages (11 files):

1.registrationScheduler.driver:
 	Driver.java, //the file which has the main method for creation of Course allocation ,Results,FileProcessor classes.
2.registrationScheduler.store:
	FileDisplayInterface.java
	Results.java
	StdoutDisplayInterface.java
3.registrationScheduler.util
	CourseAllocation.java
	FileProcessor.java  //this file used to implement file operations methods.
	ICourseAllocation.java
	Logger.java
	Student.java, //this file used for storing each student details like pref course ,allocated course etc.

README
build.xml

Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

Assuming you are in same folder as in README.
Please place the input files(input1.txt,input2.txt) and output file(output.txt) in the registrationSceduler folder.  
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=input1.txt -Darg1=input2.txt -Darg2=output.txt -Darg3=0

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

ACADEMIC HONESTY:
"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.
-ASHISH PATERIA
Sunday, February 12, 2017

DATASTRUCTURE USED:
Vector is used for storing students information as it provides search time complexity of O(n) 
and to add more students to the list, it takes find complexity of O(1).
We could have also used ArrayList,but Vector is more thread safe.


CITATIONS:
N/A


