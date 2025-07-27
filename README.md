# 📬 Notification System
### gila-notification-backend

Hello!
This is my challenge for [Gila Software](https://www.gilasoftware.com/).

It is composed of a backend application (this repo) and a frontend interface ([link](https://github.com/GHBAlbuquerque/gila-notification-frontend)).

## Description

This is a lightweight and modular backend service for delivering notifications to users based on their category and channel preferences. Users can subscribe to what they care about, and the system takes
care of routing messages to them — asynchronously and efficiently.

Available Categories:
- Movies
- Finance
- Sports

Available Channels:
- SMS
- Push
- Email

---

## 🏛️ Architecture

```
📁 gila.notification
├── 📁 adapters
│   └── 📁 controllers              # 🔹 Controllers – REST endpoints
│
├── 📁 application
│   ├── 📁 exceptions              # Domain-related exceptions
│   ├── 📁 facades                 # 🔹 Facades – Orchestrate use cases
│   ├── 📁 gateways                # 🔹 Gateway implementations
│   ├── 📁 mappers                 # Entity<->DTO conversion
│   └── 📁 usecases                # 🔹 Use Cases – Business logic
│
├── 📁 domain
│   ├── 📁 entities               # 🔹 Domain – Core entities
│   ├── 📁 enums                  # 🔹 Domain – Enums (e.g., CategoryType, ChannelType)
│   ├── 📁 interfaces
│   │   ├── 📁 gateways           # Domain gateway interfaces
│   │   ├── 📁 repositories       # Domain repository contracts
│   │   └── 📁 usecases           # Use case contracts
│
├── 📁 infrastructure
│   ├── 📁 orm                    # 🔹 ORM (JPA entities)
│   ├── 📁 config                 # 🔹 Spring Data implementations
│   └── 📁 senders                # 🔹 Notification channels (Email, SMS, etc.)
│
└── NotificationApplication.java # Main app entry point
```

---

## 🛠 Technology

- 🧠 Java 17+
- 🌱 Spring Boot 3+
- 🧰 Spring Data JPA
- 🐳 Docker + Docker Compose
- 🐘 Flyway (DB migrations and seed data)
- 🧪 JUnit 5 + Mockito + RestAssured (Testing)
- 🐬 MySQL 8 (Production) / 🧪 H2 (Testing)

---

## ▶️ How to Run

### Prerequisites

- Java 17 or newer
- Docker and Docker Compose installed (optional but recommended)

### Option 1: Running with Docker Compose

1. Navigate to the `/infra-gila-notifications` directory and run:

```bash
docker-compose up --build
```

This will start:

- MySQL database on port 3306
- Spring Boot application on port 8080

### Option 2: Running in Dev Mode with H2 (In-Memory Database)

1. Set up environment variables to point to the `application-dev.properties file`:

```
SPRING_PROFILES_ACTIVE=dev
```
2. Access the H2 console (optional):
```
http://localhost:8080/h2-console
```
### Starting the app

1. Add the app as maven project by right clicking the `pom.xml` file.
2. Install dependencies and build the app:
  
```bash
mvn clean package 
```

3. Run the app via Maven or directly from the main class `NotificationApplication`:

```bash
./mvnw spring-boot:run
```

### Accessing the app

- API Base URL: [http://localhost:8080](http://localhost:8080)
- H2 Console (for dev profile): [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User: `root`
    - Password: *(leave blank)*

4. Access the Swagger UI at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
---

## Notes

- Flyway handles schema migrations and seeding automatically, check the `resources/db/migration` directory.
- Async notification dispatching is enabled for fire-and-forget behavior.
- Sender implementation for notification is chosen in runtime through use of Strategy pattern.
- Retry mechanisms are mentioned, but not implemented, for transient failures.
- Tests cover controllers, facades, gateways, use cases and senders.

---

Made with ☕  by @GHBAlbuquerque


