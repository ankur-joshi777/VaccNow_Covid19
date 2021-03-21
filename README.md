## Technologies
Project is created with:
* Java: 8
* Springboot: 2.4.3
* H2 Database
* Spring Data JPA
* Junit
* Mockito
* Scheduler
* Swagger
* Itext for PDF Generation

## ER Diagram
![Alt text](ERD.png?raw=true "Title")
	
## Setup
To run this project:
IDE

```
Run as Java Application -> CovidVaccinationApplication.java
```
OR

```
$ mvn clean install
$ cd target
$ java -jar covid-vaccination-0.0.1-SNAPSHOT
```


## APIs

You can find details about the API contract using swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Seed Data
 1. Branches  - Branch_1 and Branch_2
 2. Vaccines  - Vaccine_A , Vaccine_2, Vaccine_3
 3. Branch_1 has Vaccine_A and Branch_2 has Vaccine_2, Vaccine_3
 3. Appointments : 
 4.  Appointment1 - user1@mail.com, Branch_1, Vaccine_A, 9:15 AM Time slot
 5.  Appointment2 - user2@mail.com, Branch_2, Vaccine_B, 10:30 AM Time slot
 6.  Appointment2 - user3@mail.com, Branch_2, Vaccine_C, 03:00 PM Time slot
 

 
 
