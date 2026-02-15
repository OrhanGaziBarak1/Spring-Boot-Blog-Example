# Spring Boot Blog Project

A RESTful social blogging platform inspired by Medium, built with Spring Boot 3.5.6 as a learning project to explore modern Java backend development.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Contributing](#contributing)

## 🎯 Overview

This project is a Medium-like blogging platform that demonstrates the implementation of a complete RESTful API using Spring Boot. It includes user authentication, article management, social features (following, favorites, claps), and a personalized timeline feed.

**Purpose**: This project was created as a hands-on learning experience to master Spring Boot and modern Java backend development practices.

## ✨ Features

### User Management
- **User Registration & Authentication**: Secure JWT-based authentication
- **User Profiles**: Public UUID-based user identification
- **Follow System**: Users can follow/unfollow other users

### Article Management
- **CRUD Operations**: Create, read, update, and soft delete articles
- **Author Attribution**: Articles are linked to their authors
- **Content Validation**: Prevents code blocks in article content
- **Pagination**: Efficient pagination for article listings

### Social Features
- **Claps**: Medium-style appreciation system (0-50 claps per user per article)
- **Favorites**: Bookmark articles for later reading
- **Timeline Feed**: Personalized feed showing articles from followed users
- **Author Pages**: View all articles by a specific author

### Technical Features
- **RESTful API Design**: Following REST best practices
- **HATEOAS Support**: Hypermedia-driven API responses
- **Database Migrations**: Liquibase for version-controlled schema changes
- **DTO Pattern**: Clean separation between entities and API responses
- **MapStruct**: Automatic object mapping
- **Validation**: Request validation using Jakarta Bean Validation
- **Soft Deletes**: Articles are soft-deleted, not permanently removed

## 🛠 Tech Stack

### Core Framework
- **Spring Boot 3.5.6** - Main application framework
- **Java 21** - Programming language

### Spring Modules
- **Spring Web** - RESTful web services
- **Spring Data JPA** - Data persistence layer
- **Spring Security** - Authentication and authorization
- **Spring HATEOAS** - Hypermedia-driven REST APIs
- **Spring Validation** - Request validation
- **Spring Actuator** - Application monitoring and management

### Database & Persistence
- **PostgreSQL** - Primary database
- **Liquibase** - Database migration and version control
- **Hibernate** - ORM framework

### Security
- **JWT (JSON Web Tokens)** - Stateless authentication
- **JJWT 0.11.5** - JWT implementation library

### Development Tools
- **Lombok** - Reduces boilerplate code
- **MapStruct 1.6.3** - Object mapping
- **Spring DevTools** - Hot reload during development
- **Spring Dotenv** - Environment variable management

### Build Tool
- **Maven** - Dependency management and build automation

## 🏗 Architecture

### Project Structure

```
src/main/java/com/blog/blog_project/
├── annotation/          # Custom annotations
├── configs/            # Configuration classes (Security, etc.)
├── controller/         # REST API endpoints
├── dto/               # Data Transfer Objects
├── entity/            # JPA entities
├── exception/         # Custom exceptions and handlers
├── mapper/            # MapStruct mappers
├── repository/        # Spring Data JPA repositories
├── services/          # Business logic layer
└── validator/         # Custom validators
```

### Core Entities

1. **User** - User accounts and authentication
2. **Article** - Blog posts/articles
3. **Follow** - User following relationships
4. **Favorite** - User's favorited articles
5. **Clap** - Article appreciation (0-50 claps per user)

### Design Patterns

- **Repository Pattern**: Data access abstraction
- **Service Layer Pattern**: Business logic separation
- **DTO Pattern**: API contract isolation
- **Builder Pattern**: Object construction (via Lombok)
- **Dependency Injection**: Spring's IoC container

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- PostgreSQL database
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd blog_project
   ```

2. **Set up PostgreSQL database**
   ```sql
   CREATE DATABASE blog_db;
   ```

3. **Configure environment variables**
   
   Copy `.env.example` to `.env` and update the values:
   ```env
   DB_URL=jdbc:postgresql://localhost:5432/blog_db
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   JWT_SECRET=your_secret_key_here
   JWT_EXPIRATION=3600000
   ```

4. **Run database migrations**
   ```bash
   mvn liquibase:update
   ```

5. **Build the project**
   ```bash
   mvn clean install
   ```

6. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080
```

### Authentication

#### Register a New User
```http
POST /users/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "Password123",
  "fullName": "John Doe"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "publicId": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Validation Rules:**
- Password: 8-16 characters, alphanumeric only
- Email: Valid email format
- Full Name: Required

#### Login
```http
POST /users/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "Password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "publicId": "550e8400-e29b-41d4-a716-446655440000"
}
```

### Articles

#### Create Article
```http
POST /article
Authorization: Bearer {token}
Content-Type: application/json

{
  "userPublicId": "550e8400-e29b-41d4-a716-446655440000",
  "content": "My first blog post content here."
}
```

**Note:** Content cannot contain code blocks.

#### Get My Articles
```http
GET /article?page=0&size=10
Authorization: Bearer {token}
```

**Response:** Paginated list of authenticated user's articles.

#### Get Articles by Author
```http
GET /article/author/{publicId}?page=0&size=10
Authorization: Bearer {token}
```

**Public endpoint** - Returns all articles by a specific author.

#### Get Single Article
```http
GET /article/{articleId}
Authorization: Bearer {token}
```

**Public endpoint** - Returns a single article by ID.

#### Update Article
```http
PATCH /article/{articleId}
Authorization: Bearer {token}
Content-Type: application/json

{
  "content": "Updated article content here."
}
```

**Authorization:** Only the article author can update their own articles.

#### Delete Article
```http
DELETE /article/{articleId}
Authorization: Bearer {token}
```

**Note:** Soft delete - article is marked as deleted, not permanently removed.

**Authorization:** Only the article author can delete their own articles.

### Favorites

#### Favorite an Article
```http
POST /favorite/{articleId}
Authorization: Bearer {token}
```

#### Unfavorite an Article
```http
DELETE /favorite/{articleId}
Authorization: Bearer {token}
```

#### Get My Favorites
```http
GET /favorite?page=0&size=10
Authorization: Bearer {token}
```

**Response:** Paginated list of favorited articles.

### Follow System

#### Follow a User
```http
POST /follow/{publicId}
Authorization: Bearer {token}
```

#### Unfollow a User
```http
DELETE /follow/{publicId}
Authorization: Bearer {token}
```

### Claps

#### Clap for an Article
```http
POST /clap/{articleId}
Authorization: Bearer {token}
Content-Type: application/json

{
  "newClap": 5
}
```

**Validation:**
- `newClap` must be between 1-50
- Each user can give 0-50 total claps per article

#### Remove Claps
```http
DELETE /clap/{articleId}
Authorization: Bearer {token}
```

**Note:** Removes all claps from the user for that article.

### Timeline

#### Get Timeline Feed
```http
GET /timeline?page=0&size=10
Authorization: Bearer {token}
```

**Response:** Paginated feed of articles from users you follow.

### Postman Collection

A complete Postman collection is available at:
```
src/main/resources/Blog-API.postman_collection.json
```

Import this collection into Postman for easy API testing. The collection includes:
- Pre-configured environment variables
- Automatic token management
- All API endpoints with example requests

## 🗄 Database Schema

### Main Tables

- **users** - User accounts
  - `id` (Long, PK)
  - `public_id` (UUID, unique)
  - `email` (String, unique)
  - `password` (String, hashed)
  - `full_name` (String)
  - `created_at`, `updated_at` (Timestamp)

- **articles** - Blog posts
  - `id` (Long, PK)
  - `user_id` (FK to users)
  - `content` (Text)
  - `is_deleted` (Boolean)
  - `created_at`, `updated_at` (Timestamp)

- **follows** - User following relationships
  - `id` (Long, PK)
  - `follower_id` (FK to users)
  - `followed_id` (FK to users)
  - `created_at` (Timestamp)

- **favorites** - Favorited articles
  - `id` (Long, PK)
  - `user_id` (FK to users)
  - `article_id` (FK to articles)
  - `created_at` (Timestamp)

- **claps** - Article appreciation
  - `id` (Long, PK)
  - `user_id` (FK to users)
  - `article_id` (FK to articles)
  - `clap_count` (Integer, 0-50)
  - `created_at`, `updated_at` (Timestamp)

### Database Migrations

Liquibase manages all database schema changes. Migration files are located in:
```
src/main/resources/db/changelog/
```

To generate a new migration:
```bash
mvn liquibase:diff
```

## 🔒 Security

### Authentication Flow

1. User registers or logs in
2. Server generates a JWT token
3. Client includes token in `Authorization: Bearer {token}` header
4. Server validates token for protected endpoints

### JWT Configuration

- **Algorithm**: HS256
- **Expiration**: Configurable via `JWT_EXPIRATION` environment variable (default: 1 hour)
- **Secret**: Configurable via `JWT_SECRET` environment variable

### Password Security

- Passwords are hashed using BCrypt
- Minimum requirements: 8-16 characters, alphanumeric

### Authorization

- **Public endpoints**: Article reading, author pages
- **Protected endpoints**: Article creation/editing, favorites, follows, claps, timeline
- **Owner-only operations**: Update/delete own articles

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### API Testing

Use the provided Postman collection for comprehensive API testing:
1. Import `Blog-API.postman_collection.json` into Postman
2. Run the "Register" or "Login" request first
3. Token and publicId are automatically saved to collection variables
4. All subsequent requests will use the saved token

## 📝 Development Notes

### Code Generation

- **Lombok**: Automatically generates getters, setters, constructors, etc.
- **MapStruct**: Generates type-safe bean mappings at compile time

### Hot Reload

Spring DevTools enables automatic application restart when code changes are detected.

### Database Schema Updates

1. Update entity classes
2. Run `mvn liquibase:diff` to generate migration
3. Review generated changeset
4. Run `mvn liquibase:update` to apply changes

## 🤝 Contributing

This is a learning project, but contributions are welcome! Please feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is created for educational purposes.

## 🙏 Acknowledgments

- Inspired by [Medium](https://medium.com)
- Built as a Spring Boot learning project
- Thanks to the Spring Boot community for excellent documentation

## 📧 Contact

For questions or feedback, please open an issue in the repository.

---

**Happy Coding! 🚀**
