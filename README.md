# Swagger generated server

Spring Boot Server 


## Overview  
This server was generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.  
By using the [OpenAPI-Spec](https://github.com/swagger-api/swagger-core), you can easily generate a server stub.  
This is an example of building a swagger-enabled server in Java using the SpringBoot framework.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

Start your server as a simple java application  

You can view the api documentation in swagger-ui by pointing to  
http://localhost:8090/  

Change default port value in application.properties

This package requires the installation of MongoDB as the underlying database. Please ensure you have the following prerequisites installed.


## Prerequisites
Homebrew

MongoDB

Maven

Tomcat Server


## Install
If you do not have Homebrew installed, please follow instructions at: http://brew.sh/

Install MongoDB by:

Run 'brew install mongodb'

Run 'brew install tomcat'

After installation, start your mongo database server (note -dbpath optional paramter)

Run 'mongod --dbpath=/Database/Mongo'

In another terminal window, execute the script included in this folder (/database/mongo.js):

Run 'mongo /database/mongo.js'

This will take care of installing the mongo dependency and creating a database with minimal test data.

Build the project with maven from the root of this directory:

Run 'mvn clean install'

This will build out a .jar file in (target/service-hmlFhirConverter-{VERSION}-SNAPSHOT.jar), simply execute the jar by java -jar {PATH_TO_JAR}, this will start an Apache server instance and host a local instance of the service at http://localhost:8090/v1. To change the port #, alter resources/application.properties.

## Code Generator

A code generation utility is provided, written in Python. This utitlity uses template (.txt) files to automatically generate Spring framework classes used in service-hmlFhirConverter.  Currently, support is present for generating CRUD operations, the following classes are automatically generated by this util:

- Controllers
- Services
- Service Implementations
- Repositories
- Custom Repositories
- Models

In addition to creating the necessary java infrastructure, the utility will compile the swagger-spec.yaml used in the maven build process.  /resources/swagger/ directory contains both a 'models' and 'paths' folder.  To add model definitions and path definitions, please create a separate .yaml file for each.  The name of the file will be reflected in the naming of the class and subsequent variable instances contained inside.  This utility composes a large swagger-spec.yaml based off the individual contents in models and paths folder. Currently, the swagger-spec.yaml composes to nearly 10,000 lines of code.  The usage of the composition process allows for a significant advantage in code managability.
    
