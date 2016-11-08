Uses Scala 2.11.8

Two Build options are provided

	SBT 0.13.13 (Recommended)
		Uses Scoverage and JUnit 4.12 for coverage/testing, the dependencies are downloaded automatically, but will take quite a while (UP TO 10 MINUTES) to download if you don't already have the exact versions
	Ant 1.9.7
		Uses JUnit 4.12 and Jacoco, the dependencies must be downloaded manually as per the 'Server setup' instructions on the 293 Blackboard page
		
		
To run tests and generate coverage report:
	SBT: "sbt test coverageReport"
	Ant: "ant report"
	
To run in the terminal:
	SBT: "sbt run"
	Ant: "ant run"
	
	Note: In order to signal the program that you are finished entering input, it is necessary to hit Enter twice after you are done, otherwise there is no way to determine what input is incomplete and erroneous versus what is just being added by a very slow typer