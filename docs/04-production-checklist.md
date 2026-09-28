# Production checklist

Before public launch:
- HTTPS
- Strong JWT secret from secret manager
- Do not keep super-admin credentials in compose files
- Rate-limit login
- Account lockout / brute-force controls
- Refresh-token strategy or short-lived access tokens
- Audit log
- MongoDB Atlas private networking where appropriate
- Automated backups
- Error monitoring
- Structured logs
- CORS restricted to the production frontend origin
- Secure headers
- Password reset/email verification
- Subscription payment gateway
- Automated tenant-isolation tests in CI
- CI/CD
- Database migration/index verification
