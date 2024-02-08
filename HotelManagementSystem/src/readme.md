# Tourism Agency System Project 🏖️

This project is a software system designed to assist tourism agencies in managing their operations digitally. It provides functionalities for managing hotel reservations, storing customer information, and conducting reservation transactions.

## Getting Started 💻

To get started with the project:

1. Open the project using a Java Integrated Development Environment (IDE) such as Eclipse or IntelliJ IDEA.
2. Examine the Java classes located in the `src` directory of the project.
3. Utilize the PostgreSQL database management tool to create the necessary database.

## Database Structure 📁

The database structure includes the following tables:

- **user**: Contains user information including username, password, and role (admin or agency staff).
- **hotel**: Stores hotel information such as name, address, contact details, and star rating.
- **season**: Records season information related to hotels, including start and end dates.
- **pension**: Stores pension type information related to hotels, with each type associated with a specific hotel.
- **room**: Contains room information including name, price, stock, and associations with hotels, seasons, and pension types.
- **reservation**: Stores reservation records related to rooms, including attributes such as name, surname, ID number, check-in and check-out dates, and room associations.

## Layered Structure 🗃️

The layered structure includes the following tables:

-**Business**:

* Houses the business logic of the application.
* Processes incoming requests, manages business processes, and enforces business rules.
* Interacts with the DAO layer to perform database operations and handles the results.
* This layer encapsulates the core functionality and rules of the application.

-**DAO (Data Access Object)**: 

* Responsible for handling database operations such as insertion, deletion, updating, and querying.
* Utilizes technologies like JDBC (Java Database Connectivity) or ORM (Object-Relational Mapping) frameworks.
* Acts as an intermediary between the Entity layer and the database, executing operations based on requests from the Business layer.

-**Core**:

* Contains code providing basic or common functionality shared across the application.
* Offers fundamental services that can be utilized by other layers.
* Typically includes shared functionalities between sub-layers, promoting code reuse and maintainability.

-**Entity**: 

* Contains Java classes representing database tables.
* Each class typically corresponds to a table and contains data fields along with getter and setter methods.
* This layer focuses on modeling the data structure of the application.

-**View**:

* Concerned with creating and presenting the user interface to the end-user.
* User interfaces, including windows or pages, are developed using technologies such as HTML, CSS, and JavaScript in web applications.
* This layer is responsible for displaying data and capturing user input.


## Usage 📋

Upon project initiation, a user interface for user login is presented.

- Authorized admin users can manage users.
- Agency staff members can conduct customer reservation transactions and manage reservations.

## Technical Requirements 🛠️

- Java
- PostgreSQL database
- IDE (IntelliJ IDEA etc.)

## Instructions 📝

To run the project:

1. Clone the project repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Set up the PostgreSQL database using the provided database structure.
4. Configure the database connection in the Java project.
5. Build and run the project in your IDE.
6. To log in as an admin, you can use the username "**admin**" and the password "**1234**".
7.  To log in as an employee, you can use the username "**employee**" and the password "**1234**"
8. Enjoy 🌞🏝️🌸🌞🌅
 


