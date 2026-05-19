# Insighta Backend

A high-performance data search and analytics backend powered by Spring Boot, featuring JWT authentication, GitHub OAuth integration, and advanced query parsing for intelligent data discovery.

## Core Features

### 🔍 Intelligent Query Parsing & Search
- **Query Parser Engine** - Processes complex search queries with support for advanced filtering, sorting, and parameter matching
- **Dynamic Specification Builder** - Constructs JPA Specifications for flexible database queries
- **Multi-field Search** - Search across profiles, data records, and indexed content
- **Full-Text Query Support** - Supports complex filter operators and boolean logic

### 🔐 Security & Authentication

#### JWT-Based Authentication
- **RSA Asymmetric Encryption** - Uses public/private key pairs for token signing and validation
- **Access & Refresh Tokens** - Separate token types with configurable expiration (180s for access, 300s for refresh)
- **Stateless Design** - No session overhead, horizontally scalable

#### GitHub OAuth 2.0 Integration
- **Social Login** - Seamless GitHub authentication flow
- **Redirect URI Handling** - Configurable OAuth callback URLs
- **User Provisioning** - Automatic user creation on first GitHub login

#### Spring Security
- **Request Filtering** - JWT filter intercepts and validates all incoming requests
- **Role-Based Access Control** - Controller-level security annotations for endpoint protection
- **Password Encoding** - Secure credential storage with BCrypt hashing

### 📊 Data Management
- **JPA/Hibernate ORM** - PostgreSQL persistence with automatic schema updates
- **Entity Models** - Type-safe domain objects with Lombok annotations for boilerplate reduction
- **Data Transfer Objects (DTOs)** - Request/response models for API contracts
- **Repository Layer** - Spring Data JPA repositories for CRUD operations

### 🎯 API Endpoints
- **RESTful Controllers** - Standard HTTP methods for resource operations
- **Request/Response Models** - Strongly-typed DTO validation
- **Exception Handling** - Centralized error handling with custom exception types

## Architecture

```
src/main/java/com/mxr/integration/
├── IntegrationApplication.java      # Spring Boot entry point
├── controller/                       # REST endpoints
├── service/                          # Business logic layer
├── repo/                             # Data access layer (JPA repositories)
├── model/                            # Entity domain models
├── dto/                              # Data transfer objects
├── security/                         # JWT & authentication filters
├── config/                           # Spring configuration
├── queryparser/                      # Query parsing & filtering
├── spec/                             # JPA Specification builders
├── filter/                           # Request/response filters
├── exceptions/                       # Custom exception types
├── Response/                         # Response wrappers
└── request/                          # Request models
```

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Spring Boot | 4.0.5 |
| **Java** | OpenJDK Temurin | 21 |
| **Database** | PostgreSQL | Latest |
| **Security** | Spring Security + JJWT | 0.12.3 |
| **ORM** | Hibernate/JPA | Bundled |
| **JSON** | Jackson | Bundled |
| **Build** | Maven | 3.9.6+ |
| **Container** | Docker | Latest |

## Configuration

### Environment Variables

```bash
# PostgreSQL
PGHOST=localhost
PGPORT=5432
PGDATABASE=insighta_db
PGUSER=postgres
PGPASSWORD=your_password

# JWT Security
JWT_PRIVATE_KEY=your_rsa_private_key
JWT_PUBLIC_KEY=your_rsa_public_key

# GitHub OAuth
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret
GITHUB_REDIRECT_URI=http://localhost:3000/auth/callback
```

### JWT Configuration
- Access Token Expiration: 180 seconds (3 minutes)
- Refresh Token Expiration: 300 seconds (5 minutes)
- Algorithm: RS256 (RSA 256-bit)

## Quick Start

### Prerequisites
- Java 21+
- Maven 3.9.6+
- PostgreSQL 13+
- Docker (optional)

### Build from Source

```bash
cd integration
mvn clean package
java -jar target/integration-*.jar
```

### Docker Deployment

```bash
docker build -t insighta-backend:latest .
docker run -p 8080:8080 \
  -e PGHOST=postgres \
  -e PGPORT=5432 \
  -e JWT_PRIVATE_KEY=$JWT_PRIVATE_KEY \
  -e JWT_PUBLIC_KEY=$JWT_PUBLIC_KEY \
  insighta-backend:latest
```

## Database Schema

The application uses Hibernate auto-schema generation (`ddl-auto=update`). Key entities:

- **User** - Application users with OAuth credentials
- **Profile** - User data profiles (indexes for search)
- **Token** - JWT token metadata for refresh/revocation
- **Search Log** - Query audit trail

## Search & Querying

### Query Parser Features
- **Filter Operators**: `=`, `!=`, `>`, `<`, `>=`, `<=`, `LIKE`, `IN`
- **Boolean Logic**: AND, OR, NOT operators
- **Sorting**: Multi-field sorting with ASC/DESC direction
- **Pagination**: Offset and limit support

### Example Query
```
profile.type=DATA AND profile.status=ACTIVE SORT BY profile.created DESC LIMIT 50 OFFSET 0
```

## Testing

Run unit tests with Maven:

```bash
mvn test
```

## Production Considerations

- Enable HTTPS/TLS for all endpoints
- Rotate JWT keys regularly
- Use environment-specific configuration profiles
- Monitor token expiration and refresh behavior
- Enable query logging for audit trails
- Set up PostgreSQL replication for HA
- Use connection pooling (HikariCP via Spring Boot)

## License

Proprietary - All rights reserved

## Author

mxrtins04
