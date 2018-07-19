# ATM Coding Challenge 

## The Brief
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

## How to use this project
This is a simple java project using SpringBoot and Hibernate in memory to allow a 
quick prototype application be created. The brief for the application is described above.

### Source Code
To use the project, simply clone the source from github using the following command 

`git clone https://github.com/annehogan/CodingChallenge0718.git`

Once you have the code, you can view it in IntelliJ. The code is in a subdirectory called `simpleatm`

### Building
The code can be built using the standard maven commands
`mvn clean package` in _simpleatm_ will create an installation jar which can be run from the command line (or in IntelliJ if you wish). 

### Running
To run the application locally use `java -jar simple-atm-0.0.1.jar`. This will run on *localhost* port *8080*. 
You can change the port by setting the server port as part of the java command line
`-Dserver.port=<new_port_number>`

### Testing
Junit testing is carried out as part of the build and Jacoco test coverage reports will also be available at `target/site/jacoco/index.html`
Given more time (and a more complex problem), I would probably set up cucumber to run integration tests. 

### Code Quality
SonarLint has been run on the code, most issues have been resolved. I have added comments explaining why I am ignoring the issues I am ignoring. Given more time I would probably supress the warnings with an explanation.

### URLS
The application allows balance queries and withdrawals. We currently use a very straightforward approach but this prototype is not concerned with security (the account number and pin are sent as part of the URL, this would not make sense for any real implementation of an ATM application)

Name|Endpoint|Description|Sample Response
:---|:---|:---:|:---|
Balance|account/{ACCOUNT_NUMBER}/pin/{PIN}|Gets the balance and maximum overdraft for account matching ACCOUNT_NUMBER and PIN|`{"currentBalance":800,"maxWithdrawal":1000}`
Withdrawal|account/{ACCOUNT_NUMBER}/pin/{PIN}/withdraw/{AMOUNT}|Withdraws the AMOUNT from account matching ACCOUNT_NUMBER and PIN|`{"banknotePiles":[{"noteValue":50,"noteCount":20},{"noteValue":20,"noteCount":19}],"remainingBalance":-150}`


To get balances use the URL `http://<host>:<port>/account/<account>/pin/<pin>` or in the 
simplest example without having changed the host and port `http://localhost:8080/account/123456789/pin/1234`. 

To make a withdrwal use the URL `http://<host>:<port>/account/<account>/pin/<pin>/withdraw/<amount>` or in the 
simplest example without having changed the host and port `http://localhost:8080/account/123456789/pin/1234/withdraw/5`. 

#### Error Codes

Code|Description
:---:|:---:|
ATM_ERR_0001|You must request a positive amount
ATM_ERR_0002|There are insufficient funds in the account
ATM_ERR_0003|There are insufficient funds in the atm
ATM_ERR_0004|The account number and pin have matched multiple accounts
ATM_ERR_0005|There is no account matching account number and pin
ATM_ERR_0006|You must use a numeric value for account number, pin and amount.

Sample Error Message
`{"code":"ATM_ERR_0002","errorMessage":"Not enough funds in your account"}`

## Database
The database provided is a hibernate in memory database that allows us to prepopulate some data using a `src\main\resources\data.sql` file. To add extra data for test there is a second file `src\test\resources\data.sql`

### H2 Console
The H2 console has been enabled so that you can view the live DB while the application is running. 

It can be accessed with this URL:

`http://localhost:8080/h2-console`

In the login page enter this JDBC URL 

`jdbc:h2:mem:testdb`

Use the driver `org.h2.Driver` and use the `sa` username with no password.

## Who to contact
Contact [me](mailto:annehogan.ie@gmail.com) for more information


