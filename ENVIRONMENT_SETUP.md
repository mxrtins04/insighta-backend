# Environment Configuration

This document describes the environment variables required to run the Insighta Labs+ backend.

## Database Variables

These variables are already configured in your environment for PostgreSQL:

- `PGHOST` - PostgreSQL host
- `PGPORT` - PostgreSQL port
- `PGDATABASE` - PostgreSQL database name
- `PGUSER` - PostgreSQL username
- `PGPASSWORD` - PostgreSQL password

## JWT RSA Keys

Generate RSA 2048-bit keys in PEM format and set the following environment variables:

```bash
# Generate private key
openssl genrsa -out private_key.pem 2048

# Generate public key
openssl rsa -in private_key.pem -pubout -out public_key.pem

# Set environment variables (convert to single line, remove newlines)
export JWT_PRIVATE_KEY=$(cat private_key.pem | tr -d '\n')
export JWT_PUBLIC_KEY=$(cat public_key.pem | tr -d '\n')
```

## GitHub OAuth

Create a GitHub OAuth application at https://github.com/settings/developers and set:

- `GITHUB_CLIENT_ID` - GitHub OAuth application client ID
- `GITHUB_CLIENT_SECRET` - GitHub OAuth application client secret
- `GITHUB_REDIRECT_URI` - OAuth callback URL (e.g., http://localhost:8080/auth/github/callback)

## Required Environment Variables Summary

- `PGHOST`
- `PGPORT`
- `PGDATABASE`
- `PGUSER`
- `PGPASSWORD`
- `JWT_PRIVATE_KEY` (PEM format, single line)
- `JWT_PUBLIC_KEY` (PEM format, single line)
- `GITHUB_CLIENT_ID`
- `GITHUB_CLIENT_SECRET`
- `GITHUB_REDIRECT_URI`

## Example .env File

```
PGHOST=localhost
PGPORT=5432
PGDATABASE=insighta
PGUSER=postgres
PGPASSWORD=your_password
JWT_PRIVATE_KEY="-----BEGIN PRIVATE KEY-----...-----END PRIVATE KEY-----"
JWT_PUBLIC_KEY="-----BEGIN PUBLIC KEY-----...-----END PUBLIC KEY-----"
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret
GITHUB_REDIRECT_URI=http://localhost:8080/auth/github/callback
```

## Running the Application

Ensure all environment variables are set before starting the application:

```bash
# Load environment variables from .env file (if using)
source .env

# Or export them manually
export PGHOST=localhost
export PGPORT=5432
# ... etc

# Run the application
./mvnw spring-boot:run
```
