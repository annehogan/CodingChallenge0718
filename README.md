# CodingChallenge0718

##ATM Coding Challenge 

### How to use this project
This is a simple java project using SpringBoot and Hibernate in memory to allow a quick prototype application be created. The brief for the application is described [here](simpleatm/README.md) 

###Source Code
To use the project, simply clone the source from github using the following command `git clone https://github.com/annehogan/CodingChallenge0718.git`
Once you have the code, you can view it in IntelliJ.

###Building
The code can be built using the standard maven commands.
`mvn clean package` will create an installation jar which can be run from the command line (or in IntelliJ if you wish). 

###Running
To run the application locally use `java -jar simple-atm-0.0.1.jar`. This will run on *localhost* port *8080*. 
You can change the port by setting the server port as part of the java command line
`-Dserver.port=<new_port_number>`

###Testing
Junit testing is carried out as part of the build and Jacoco test coverage reports will also be available at `target/site/jacoco/index.html`

###The Brief
You are tasked with developing software for an ATM machine. The software is responsible for validating customer account details and performing basic operations including balance inquiries and cash withdrawals.
A third party is developing the UI and will provide data to the application in an agreed format defined below. The application should receive the data, process the operations and then output the results in the required format also defined below. For the purposes of the test you are free to implement any mechanism for feeding input into your solution.
The solution should meet the following business requirements:
-	The ATM cannot dispense more money than it holds.
-	The ATM has a starting number of notes in the following denominations, €5, €10, €20, €50
-	The customer cannot withdraw more funds then they have access to.
-	The ATM should not dispense funds if the pin is incorrect.
-	The ATM should not expose the customer balance if the pin is incorrect.
-	The ATM should only dispense the exact amounts requested.
-	The ATM should dispense the minimum number of notes per withdrawal
-	The ATM should initialize with €2000 made up of 20 x €50s, 30 x €20s, 30 x €10s and 20 x €5s
-	The ATM should also initialize with the following accounts:

Account Number|PIN|Opening Balance|Overdraft
---|---|---|---|
123456789|1234|800|200
987654321|4321|1230|150

Input / Output
-	The user should be able to request a balance check along with maximum withdrawal amount (if any).
-	The user should be able to request a withdrawal and if successful details of the notes that would be dispensed along with remaining balance.
-	User should be provided with meaningful error messages.

