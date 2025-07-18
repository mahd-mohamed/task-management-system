
```
#Task Management System using Spring Boot & PostgreSQL
A backend RESTful API application built with Spring Boot that provides full task management functionality.  
This system allows users to manage tasks, projects, and users, with features like assignment, status tracking, and priority handling.
---
Features
- Full CRUD operations for:
  - Users
  - Projects
  - Tasks
- Assign tasks to users within specific projects.
- Task attributes:
  - `status` (TODO, IN_PROGRESS, DONE)
  - `priority` (HIGH, MEDIUM, LOW)
  - `dueDate`
- Filtering capabilities:
  - Filter by project
  - Filter by assigned user
  - Filter by status or priority
- Database: PostgreSQL(Managed via pgAdmin)
- DTO layer for clean API structure
- API tested via Postman
---

Tech Stack
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Postman
- pgAdmin
---
Project Structure
├── controller/
├── service/
├── repository/
├── dto/
├── model/
└── config/
---
API Endpoints (Examples)
GET    /users
POST   /users
GET    /users/{id}
PUT    /users/{id}
DELETE /users/{id}

GET    /projects
POST   /projects
GET    /projects/{id}
PUT    /projects/{id}
DELETE /projects/{id}

GET    /tasks
POST   /tasks
GET    /tasks/{id}
PUT    /tasks/{id}
DELETE /tasks/{id}

GET    /tasks/user/{userId}
GET    /tasks/project/{projectId}
GET    /tasks/status/{status}
GET    /tasks/priority/{priority}
---
Author
Created with  by Eng. Mahd
 Feel free to fork, contribute, or give it a if you found this helpful!
---
