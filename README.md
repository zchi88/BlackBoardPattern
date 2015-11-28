# About the HeartbeatTactics Project
The purpose of this project is to practice implementing the Heartbeat architectural tactic. This tactic aims to improve
the availability of a system by monitoring the status of a crtical component to ensure that it is alive and running.
In this example, I have modeled a pacemaker system. I identified the critical component of this system as being the
sensor which detects heart pulses sent by a simulated heart. 

## Architectural Overview
![Pacemaker with blackboard pattern](https://raw.githubusercontent.com/zchi88/HeartbeatTactic/master/BlackboardPatternPacemaker.png "Pacemaker with blackboard pattern")

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
3. Change directory to `...PATH/TO/HeartbeatTactic/bin`
4. Run PacemakerController with the following command:

	```console
	java pacemaker.PacemakerController
	```

5. Open a second command line client
6. Change working directory to same as before (`...PATH/TO/HeartbeatTactic/bin`)
7. Run HeartSimulator with the following command:

	```console
	java pacemaker.HeartSimulator
	```

8. Thats it! A GUI should pop up and show you that the pacemaker is running.


