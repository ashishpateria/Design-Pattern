CS542 Design Patterns
Spring 2017
PROJECT 2 README FILE

Due Date: Tuesday, March 7, 2017
Submission Tuesday, March 7, 2017
Author: Ashish Pateria
Email-id:apateri1@binghamton.edu

Project Detail:
PURPOSE:
In this project, course allocation is done using multi threading. There are 8 courses each having 60 seats available and 80 students are required to give 5 preferences.
5 courses would be allocated to these students most likely according to their preferences.
preference score will be calculated according to the courses they get. 
If a student gets her first choice, the preference score is 6. If a student gets her second choice, the preference score is 5, and so on. 
At the end average preference score of the whole class is calculated.
Upto 3 threads can work on allocation process. Object pool is created for 8 courses which will keep it synchronized.


Debug levels:

DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
DEBUG_VALUE=2 [Print to stdout everytime an entry is added to the Results data structure]
DEBUG_VALUE=1 [Print to stdout the contents of the data structure in the store]
DEBUG_VALUE=0 [No output should be printed from the application, except the line "The average preference value is X.Y" ]

Percentage Completion:
I believe I have 100% completed.

BUGS:
None

FILES:
Included with this project are 5 packages (16 files):

1.registrationScheduler.driver:
 	Driver.java, the file which has the main method and is responsible for course allocation methods.
2.registrationScheduler.objectPool:	
	CoursePool.java
	ObjectPool.java
3.registrationScheduler.store:
	FileDisplayInterface.java
	Results.java
	StdoutDisplayInterface.java
4.registrationScheduler.threadMgmt
	CreateWorkers.java
	WorkerThread.java
5.registrationScheduler.util
	Scheduler.java
	FileProcessor.java
	IScheduler.java
	Logger.java
	Student.java, the file responsible for storing student details.
	
README
build.xml

SAMPLE OUTPUT:







ACADEMIC HONESTY:
"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plaigarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.
-ASHISH PATERIAù
Tuesday, March 7, 2017

DATASTRUCTURE USED:
Vector is used for storing students information as it provides search time complexity of O(n) 
and to add more students to the list, it takes find complexity of O(1).
We could have also used ArrayList,but Vector is more thread safe.

Used HashTable to store course capacity info.It is used ad it is threadsafe


CITATIONS:
N/A


