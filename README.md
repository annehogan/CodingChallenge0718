# CodingChallenge0718

## How to use this project
This is a simple java project using SpringBoot and Hibernate in memory to allow a quick prototype application be created. 

###Source Code
To use the project, simply clone the source from github using the following command `git clone https://github.com/annehogan/CodingChallenge0718.git`
Once you have the code, you can view it in IntelliJ. 

###Building
The code can be built using the standard maven commands
`mvn clean package` will create an installation jar which can be run from the command line (or in IntelliJ if you wish). 

###Running
To run the application locally use `java -jar simple-atm-0.0.1.jar`. This will run on *localhost* port *8080*. 
You can change the port by setting the server port as part of the java command line
`-Dserver.port=<new_port_number>`

###Testing
Junit testing is carried out as part of the build and Jacoco test coverage reports will also be available at `target/site/jacoco/index.html`