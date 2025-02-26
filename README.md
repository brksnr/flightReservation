# 🚀 Flight Reservation System  
A system where users can create an account, make flight reservations, and complete payment transactions.  

## **🛠️ Technologies Used**  
- **Spring Boot** – For backend development  
- **Spring Security** – For authentication and authorization  
- **JPA (Hibernate)** – For database management  
- **PostgreSQL** – As the database  
- **Validation** – For user input validation  

## **🔑 API Endpoints**  

| Method  | Endpoint | Description |
|---------|--------------------------------|------------------------------|
| **POST** | `/auth/register` | Register a new user |
| **POST** | `/auth/login` | User login |
| **GET**  | `/member/{userId}` | Get member by ID |
| **POST** | `/info/update/{userId}` | Update user information |
| **POST** | `/creditcard/save/{userId}` | Add a credit card |
| **DELETE** | `/creditcard/delete/{userId}/{creditCardId}` | Delete a saved credit card |
| **POST** | `/planet/add` | Add a planet |
| **DELETE** | `/planet/delete/{planetId}` | Delete a planet |
| **POST** | `/ship/add` | Add a ship |
| **DELETE** | `/ship/delete/{shipId}` | Delete a ship |
| **POST** | `/seat/add/{shipId}` | Add a seat to a ship |
| **POST** | `/flight/add` | Add a flight |
| **DELETE** | `/flight/delete/{flightId}` | Delete a flight |
| **GET** | `/flight/get/{flightId}` | Get flight by ID |
| **GET** | `/flight/all` | Get all flights |
| **POST** | `/reservations/create/{flightId}/{userId}/{creditCardId}` | Make a reservation |
| **DELETE** | `/reservations/delete/{userId}/{reservationId}` | Cancel a reservation |
| **POST** | `/payments/pay` | Process a payment |

## **👨‍💻 Berk Şener**  
🔗 [LinkedIn](https://www.linkedin.com/in/berksener)
