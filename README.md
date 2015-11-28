# About the BlackboardPattern Project
The purpose of this project is to practice implementing the Blackboard pattern. This project extends upon the 
Heartbeat Tactic pacemaker project, and adds additional components as detailed in the blackboard pattern
to help the system process and generate knowledge. 

## Architectural Overview
![Pacemaker with blackboard pattern](https://github.com/zchi88/BlackBoardPattern/blob/master/BlackboarPatternPacemaker.png?raw=true "Pacemaker with blackboard pattern")

## Dependencies
This application was created with JDK 8 and Eclipse

## Running the App From Eclipse:
1. Clone or copy this repository to your local machine
2. Open up Eclipse, and import this project

	```Eclipse
	File > Import... > General > Existing Projects into Workspace > PATH TO PROJECT
	```

3. Run PacemakerController 
4. Run HeartSimulator 
5. Thats it! A GUI should pop up and show you that the pacemaker is running.



## Running the App From the Command Line:
1. Clone or copy this repository to your local machine
2. Open up command line client
3. Change directory to `...PATH/TO/BlackboardPattern/bin`
4. Run PacemakerController with the following command:

	```console
	java pacemaker.PacemakerController
	```

5. Open a second command line client
6. Change working directory to same as before (`...PATH/TO/BlackboardPattern/bin`)
7. Run HeartSimulator with the following command:

	```console
	java pacemaker.HeartSimulator
	```

8. Thats it! A GUI should pop up and show you that the pacemaker is running.


