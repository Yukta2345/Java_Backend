# Spring Boot Insurance Backend

This is a **Spring Boot backend** for managing insurance policies and purchases. It provides RESTful APIs for CRUD operations on insurance policies and purchase records. The application is deployed on **Railway**.

---

## 🚀 Features
- **Insurance Management**: Create, Read, Update, and Delete insurance policies.
- **Purchase Handling**: Manage insurance purchases.
- **PDF Generation**: Generate policy documents.
- **Spring Data JPA**: Uses Hibernate with a database.
- **Unit Testing**: JUnit & Mockito for testing.
- **Deployed on Railway**: Public API accessible via Railway.

---

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot** (Spring MVC, Spring Data JPA, Spring Boot Starter Web)
- **H2 Database / MySQL** (Configurable)
- **Maven**
- **JUnit 5 & Mockito** (Testing)

---

## 📂 Project Structure
```
├── src
│   ├── main
│   │   ├── java/com/example/demo
│   │   │   ├── controller  # REST Controllers
│   │   │   ├── model       # Entity Models
│   │   │   ├── repository  # JPA Repositories
│   │   │   ├── service     # Business Logic
│   │   │   ├── utils       # Utility Classes
│   │   ├── resources
│   │   │   ├── application.properties  # Configurations
│   ├── test  # Unit tests
└── pom.xml  # Maven Dependencies
```

---

## 📌 API Endpoints
### **Insurance Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/api/insurance` | Get all insurance policies |
| GET | `/api/insurance/{id}` | Get a single policy by ID |
| POST | `/api/insurance` | Create a new policy |
| PUT | `/api/insurance/{id}` | Update a policy |
| DELETE | `/api/insurance/{id}` | Delete a policy |

### **Purchase Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/api/purchase` | Get all purchases |
| POST | `/api/purchase` | Make a new purchase |

---

## 🎯 Setup Instructions
### **1️⃣ Clone the Repository**
```sh
git clone <repo-url>
cd <repo-folder>
```

### **2️⃣ Configure Database**
Modify `src/main/resources/application.properties` for **H2 (in-memory)** or **MySQL**:

#### ✅ **H2 Database (default for testing)**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

#### ✅ **MySQL (Production-ready)**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### **3️⃣ Build & Run the Project**
#### Run using Maven
```sh
mvn clean install
mvn spring-boot:run
```
The application will be available at **`http://localhost:8080`**

---

## 🛠️ Running Tests
To run unit tests:
```sh
mvn test
```
---

## 🚀 Deployment on Railway
This project is deployed on **Railway**. Your public API is available at:
```
https://javabackend-production-8f64.up.railway.app
```

### **Using GET Requests in Browser**
- Open: `https://javabackend-production-8f64.up.railway.app/api/insurance`

### **Making a POST Request (Without Postman)**
Use **HTML form** or **JavaScript fetch API**:

#### ✅ **Using JavaScript Fetch**
```js
fetch('https://javabackend-production-8f64.up.railway.app/api/insurance', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        name: 'Life Insurance',
        price: 10000
    })
}).then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));
```

---

## 🤝 Contributing
1. **Fork** the repo
2. **Create a feature branch** (`git checkout -b feature-branch`)
3. **Commit changes** (`git commit -m 'Add feature'`)
4. **Push to branch** (`git push origin feature-branch`)
5. **Create Pull Request**

---

## 📝 License
This project is licensed under the **MIT License**.

---

## 📞 Contact
For any issues, contact:
📧 **Your Email**

---

### ⭐ If you like this project, give it a star! ⭐

