# RSA Key Generation Instructions

Generate RSA 2048-bit keys in PEM format for JWT signing.

## Generate Private Key

```bash
openssl genrsa -out private.pem 2048
```

## Convert to PKCS8 format (required for Java)

```bash
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in private.pem -out private_pkcs8.pem
```

## Extract Public Key

```bash
openssl rsa -in private_pkcs8.pem -pubout -out public.pem
```

## Environment Variables

Set these environment variables with the full PEM content including headers:

```bash
export JWT_PRIVATE_KEY="-----BEGIN PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC...
-----END PRIVATE KEY-----"

export JWT_PUBLIC_KEY="-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA...
-----END PUBLIC KEY-----"
```

## For .env file

```env
JWT_PRIVATE_KEY="-----BEGIN PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC...
-----END PRIVATE KEY-----"

JWT_PUBLIC_KEY="-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA...
-----END PUBLIC KEY-----"
```

Note: The keys above are examples. Replace with your actual generated keys.
