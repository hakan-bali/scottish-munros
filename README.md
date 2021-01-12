# Overview
This project has been developed with Java 11 source code compatibility to provide a simple API set for querying Scottish Munros given within a CSV formattd file.

---
## Pre-requisites for the project
[Install](https://www.oracle.com/uk/java/technologies/javase-jdk11-downloads.html) JDK version 11

[Install](https://gradle.org/install/) Gradle version 6.6

[Install](https://git-scm.com/downloads) Git version 2.30.0

---
## Libraries Used to Develop the Application
| Library Name | Version |
| --- | --- |
| Spring Boot | [2.4.1](https://github.com/spring-projects/spring-boot/releases/tag/v2.4.1) |
| Open CSV | [5.3](http://opencsv.sourceforge.net/) |
| JUnit5 for unit testing | [5.7.0](https://junit.org/junit5/) |
| Lombok for boilerplate code generation | [1.18.16](https://projectlombok.org/) |
| Mockito for unit test mocking | [3.6.28](https://site.mockito.org/) |

---
## A fresh startup for the project
Go to any directory you'd like to create the project.
```
$ cd <ANY_DIRECTORY>
```
Clone the project from GitHub repository. Project files will be placed under <ANY_DIRECTORY>/grocery-scraper
```
$ git clone https://github.com/hakan-bali/scottish-munros.git
```
Proceed to the project directory.
```
$ cd scottish-munros
```
Gradle will download all required dependencies, build the project, and run the project with the command below:
```
$ ./gradlew run
```

---
### Clean build whole project from command line
```
$ ./gradlew clean build
```

---
### Running unit tests from command line
```
$ ./gradlew test
```

---
### Check that it is running
```
$ ./gradlew run
```

---
### Requirements Compliance Matrix
| # | Functional Requirements | Compliance |
| --- | --- | --- |
| 1 | The goal of the solution is to create a simple API which other software can use to sort and filter the munro data. | Done |
| 2 | Solution should be developed using either Java or Kotlin.  | Done |
| | Developed in Java | |
| 3 | Any relevant libraries or frameworks, including application frameworks such as Spring or Micronaut are welcome. | Done |
| | Developed with Spring Boot | |
| 4 | Any database (in-memory or otherwise) utilisation is not permitted to implement the search functionality. | Done |
| | All Munro data is kept in a List, and read from the CSV file at the beginning of the App. | |
| | Search, Sort, and Limit functionality implemented with Java Stream API. | |
| 5 | Filtering of search by hill category (i.e. Munro, Munro Top or Either). | Done |
| | 'MUN' for Munro, 'Top' for Munro Top, 'Either' for Either. Category namings are not case-sensitive. | |
| 6 | If Munro category is not provided, default is 'Either'. | Done |
| 7 | Category info will be acquired from the “post 1997” column. | Done |
| 8 | If category info is blank (aka Null), the hill should be always excluded from results. | Done |
| 9 | The search (query) ability to sort the results by height in meters and alphabetically by name. | Done |
| 10 | The search (query) ability to sort the results ascending or descending order for both sorting fields. | Done |
| 11 | The search (query) to limit the total number of results returned, e.g. only show the top 10. | Done |
| | Default value for 'Limit' is 10 when it's not given. | |
| 12 | The search (query) ability to specify a minimum height in meters. | Done |
| 13 | The search (query) ability to specify a maximum height in meters. | Done |
| 14 | The search parameters may include any combination of Category, Max Height, and Min Height. | Done |
| 15 | None of the search parameters are mandatory. | Done |
| 16 | Suitable error responses for invalid search parameters or irrational combinations like the max height is less than the minimum height. | Done |
| 17 | The ability to combine sort criteria in order of preference. | Done |
| | 'H' for 'Height Ascending', 'h' for 'Height Descending' | |
| | 'N' for 'Name Ascending', 'n' for 'Name Descending' | |
| | Default is 'HN' if it's not given. Samples are as follows: 'hn', 'nH', 'Nh', 'Hn'| |
| 18 | The query results should be returned as a list of items using JSON. | Done |
| 19 | Each result item should contain the name, height in meters, hill category and grid reference (e.g. NN773308). Other fields should not be included. | Done |
