# L'atelier technical test project

### Overview
Technical test project developed with spring boot, the main purpose of this project is to give an overview of the spring boot capabilities as a framework
for consuming and exposing RESP API's, executing persistence actions on the in memory H2 database using hibernate ORM, and creating unit tests with the help
of JUnit framework, Mockito and AssertJ library.
The data being exposed is related to Tennis Players.

### Running instruction
With maven you can `mvn clean install` to compile the source code, run tests, and packages the project into a distributable format.
You can then run java command to execute the application using `java -jar l-atelier-test-technique.jar` under /target folder.
You can also build the project and run it in a container using the Dockerfile and the docker commands `docker build & docker run image-id`.
The project is up and running on an AWS service on the public ip `3.81.94.160:8080`

### API endpoints being exposed
**/players**
Returns the list of players fetched from the "latelier" external API.

**/players/ordered**
Returns the list of players ordered by rank.

**/player/{id}**
Returns the player with the specified id.

**/stats**
Returns the stats of all the players present on the database.
