# Blog Project - Claude Context

## Project Overview
A RESTful social blogging platform backend built with Spring Boot 3.5.6 and Java 21. Users can write articles, follow other users, favorite articles, and "clap" for articles (similar to Medium).

## Tech Stack
- **Framework**: Spring Boot 3.5.6
- **Language**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA / Hibernate
- **Authentication**: Spring Security + JWT (JJWT 0.11.5)
- **DB Migrations**: Liquibase
- **Mapping**: MapStruct
- **Code Generation**: Lombok
- **Build**: Maven

## Project Structure
```
src/main/java/com/blog/blog_project/
├── controller/      # REST API endpoints
├── services/        # Business logic (interfaces + impl/)
├── entity/          # JPA entities
├── repository/      # Spring Data JPA repositories
├── dto/             # Data Transfer Objects
├── mapper/          # MapStruct mappers
├── exception/       # Custom exceptions & GlobalExceptionHandler
├── annotation/      # Custom validation annotations
├── validator/       # Custom validators
└── configs/         # Security & JWT configuration
```

## Key Entities
| Entity | Table | Description |
|--------|-------|-------------|
| User | users | User accounts with UUID publicId |
| Article | article | Blog posts with soft delete support |
| Favorite | favorites | User-article favorites |
| Follow | follows | User-user follow relationships |
| Clap | claps | Article claps (0-50 per user per article) |

## API Endpoints
| Path | Controller | Description |
|------|------------|-------------|
| POST /users/register, /users/login | AuthenticationController | User registration & login |
| /article/* | ArticleController | Article CRUD operations |
| /favorite/* | FavoriteController | Favorite/unfavorite articles |
| /follow/* | FollowController | Follow/unfollow users |
| /clap/* | ClapController | Clap for articles |
| /timeline | TimelineController | Feed from followed users |

## Configuration
- **application.properties**: DB connection, JPA settings, JWT config
- **Environment variables**: DB_URL, DB_USERNAME, DB_PASSWORD, JWT_SECRET, JWT_EXPIRATION
- **Database migrations**: `src/main/resources/db/changelog/`

## Development Commands
```bash
# Run the application
./mvnw spring-boot:run

# Build the project
./mvnw clean package

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Conventions
- DTOs use `@NotNull`, `@NotBlank`, and custom `@NoCode` validation
- Soft delete pattern for articles (isDeleted flag)
- UUID-based public IDs for users (publicId field)
- Paginated responses use PagedResponseDTO wrapper
- Centralized exception handling via GlobalExceptionHandler

## Database Migration Rules
- Liquibase changesets must be created manually (do not use `liquibase diff` command)
- Changeset file format must be `.sql`
- Naming convention: `changeset-yyyy-mm-dd-hhmm.sql` (date and time of file creation)
- Location: `src/main/resources/db/changelog/changesets/`
