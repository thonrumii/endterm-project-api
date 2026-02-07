**A. Project Overview**

This project is a Spring Boot RESTful API for managing workouts and exercises in a fitness tracking system.
It is a continuation of previous JDBS-based assignment with a MySQL database that follows a layered architecture.

The project demonstrates:
- REST API development with Spring boot
- Manual JDBC database interaction
- SOLID principles
- Advanced OOP principles
- Design patterns & component principles

The application exposes HTTP endpoints that can be accessed/tested using Postman or e web-browser.

**B. REST API Documentation**

Base URL: `http://localhost:8081`
- Endpoint List
  1. Workouts Endpoints
  ```
    Get all workouts
    Method: GET
    Endpoint: /api/workouts
  
    Sample response:
    [
      {
        "id": 1,
        "name": "Morning Run",
        "duration": 30,
        "workoutType": "CARDIO",
        "trackingInfo": "CARDIO: 195.0 calories",
        "trackingInfoPretty": "[TRACK] CARDIO: 195.0 calories"
      }
    ]
  ```
  ```
    Create workout
    Method: POST
    Endpoint: /api/workouts

    Sample request:
    {
    "name": "Evening Cardio",
    "duration": 40,
    "type": "CARDIO"
    }
  
    Sample response:
    {
    "id": 2,
    "name": "Evening Cardio",
    "duration": 40,
    "workoutType": "CARDIO",
    "trackingInfo": "CARDIO: 260.0 calories",
    "trackingInfoPretty": "[TRACK] CARDIO: 260.0 calories"
    }
  ```
  ```
    Get workout by ID
    Method: GET
    Endpoint: /api/workouts/{id}
  
    Sample response:
    {
    "id": 1,
    "name": "Morning Run",
    "duration": 30,
    "workoutType": "CARDIO",
    "trackingInfo": "CARDIO: 195.0 calories"
    }

  ```
  ```
    Update workout
    Method: PUT
    Endpoint: /api/workouts/{id}

    Sample request:
    {
    "name": "Updated Workout",
    "duration": 50
    }
  ```
  ```
    Delete workout
    Method: DELETE
    Endpoint: /api/workouts/{id}
  ```
  2. Exercises Endpoints
  ```
    Add exercise to workout
    Method: POST
    Endpoint: /api/workouts/{workoutId}/exercises

    Sample request:
    {
    "name": "Push Ups",
    "sets": 3,
    "reps": 15
    }
  ```
  ```
    Get exercises by workout
    Method: GET
    Endpoint: /api/workouts/{workoutId}/exercises
  
    Sample response:
    [
      {
      "id": 1,
      "name": "Push Ups",
      "sets": 3,
      "reps": 15
      }
    ]

  ```
  ```
    Get exercise by ID
    Method: GET
    Endpoint: /api/exercises/{id}
  
    Sample response:
    {
    "id": 1,
    "name": "Push Ups",
    "sets": 3,
    "reps": 15
    }
  ```
  ```
    Update exercise
    Method: PUT
    Endpoint: /api/exercises/{id}
  ```
  ```
    Delete exercise
    Method: DELETE
    Endpoint: /api/exercises/{id}
  ```

- Postman screenshots

Screenshots can be accessed at: `docs/screenshots`.

**C. Design Patterns Section**

- Singleton Pattern
  - Purpose: Ensure a single database configuration instance.
  - Implemented in `DbConfigSingleton`
  - Guarantees one shared JDBC configuration across the application.
- Factory Pattern
  - Purpose: Create different workout types without exposing creation logic.
  - `WorkoutFactory` returns either `CardioWorkout` or `StrengthWorkout`
  - Uses abstract base type `Workout`
  - Supports easy extension for new workout types
- Builder Pattern
  - Purpose: Simplify and control object creation for complex objects.
  - Implemented for creating `Exercise`
  - Improves readability and avoids large constructors


**D. Component Principles Section**

REP (Reuse/Release Equivalence Principle)
Reusable components such as repositories and utilities are grouped logically.

CCP (Common Closure Principle)
Classes that change together (services + exceptions) are grouped together.

CRP (Common Reuse Principle)
No module forces dependencies on unused classes or functionality.

**E. SOLID & OOP Summary**

- Single Responsibility Principle:
Each layer has one responsibility (controller, service, repository, model).
- Open–Closed Principle:
New workout types can be added without modifying existing code.
- Liskov Substitution Principle:
CardioWorkout and StrengthWorkout can replace Workout safely.
- Interface Segregation Principle:
Small, focused interfaces (ITrackable, IValidatable, query interfaces).
- Dependency Inversion Principle:
Services depend on repository interfaces, not implementations.

Advanced OOP concepts used:
* Abstract classes
* Interfaces with default and static methods
* Polymorphism
* Generics
* Reflection

**F. Database Schema**

Database: fitness_tracker

Tables:
- workout
```
id (PK)
name (UNIQUE, NOT NULL)
type
duration_minutes
```
- exercises
```
id (PK)
workout_id (FK)
name
sets
reps
```
Constraints:
* workout.name is UNIQUE
* exercises.workout_id references workout.id
* ON DELETE CASCADE removes exercises when a workout is deleted

**G. System Architecture Diagram**

System architecture and UML diagrams can be accessed at `docs/` folder.

These diagrams illustrate:
* Layered architecture
* Class relationships
* Inheritance and composition

H. Instructions to Run the Spring Boot Application

Requirements:
* Java JDK 17
* MySQL Server 8+
* Maven

Create database:
```
CREATE DATABASE fitness_tracker;
```

Configuration: update application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/fitness_tracker
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
server.port=8081
```

In IntelliJ IDEA:
- Run `EndtermProjectApplication`
- Access the API either via browser or Postman:
```
http://localhost:8081/api/workouts
```

I. Reflection Section

This project helped me deepen my understanding of JDBC, REST APIs, and layered system design.
I learned how SOLID principles improve maintainability, extensibility, and readability in real applications.

The main challenges were configuring JDBC connections, managing validation across layers, and refactoring the code to apply dependency inversion properly. Despite these challenges, the project clearly demonstrated the advantages of a well-structured, multi-layer architecture.