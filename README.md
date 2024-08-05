# madeline2-server
A front-end web server to the madeline2 command-line application. 
[Ubuntu full installation guide available here](INSTALLAION_UBUNTU.MD)

To set up the Madeline2 server from the GitHub repository, you'll need to follow these steps:

## System Requirements:

* Java 8 or higher
* Tomcat 8

## Download the Repository:

* Clone the repository from GitHub: madeline2-server.

```
git clone https://github.com/AustralianNationalUniversity/madeline2-server.git
cd madeline2-server
```

## Build the Project:

* Navigate to the project directory and use gradle to build the project. You can use ./gradlew build if you're on a Unix-like system or gradlew.bat build on Windows.

- On Linux-like systems
```
./gradlew build
```

- On Windows:
```
gradlew.bat build
```

This will generate a WAR file in the build/libs directory.

## Deploy to Tomcat:

* Once built, deploy the generated WAR file (located in the build/libs directory) to your Tomcat server.

```
/path/to/tomcat/webapps/
```

## Configuration:

* Ensure that your Tomcat server is correctly configured to handle the deployed application. You may need to adjust memory settings or other server parameters depending on your deployment environment.

## Running the Server:

* Start your Tomcat server and navigate to the application URL (e.g., http://localhost:8080/madeline2-server).

## Demo server:

http://130.56.244.179/madeline2-server


## 9. Monitoring and Logs

* Check the Tomcat logs for any issues. Logs are typically located in the logs directory of the Tomcat installati

- For further details or troubleshooting, you may refer to the official documentation and Apache Tomcat documentation.
