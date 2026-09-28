# Coaching SaaS Platform

Production-oriented MVP for coaching/tuition institutes.

## Stack
- Java 25
- Spring Boot 3.5.5
- Spring Security + JWT + BCrypt
- MongoDB
- Angular 20
- Docker Compose
- Postman collection

## Included end-to-end workflows
1. Coaching owner signup/login
2. JWT authentication and role authorization
3. Multi-tenant isolation using authenticated `coachingId`
4. Teacher creation with temporary password
5. Teacher login
6. Batch management
7. Student management and batch assignment
8. Class-session creation
9. Teacher attendance marking
10. Attendance reporting
11. Fee structures/installments
12. Fee payment recording
13. Paid/pending/partial fee status
14. Tests and student results
15. Timetable
16. Coaching dashboard
17. Super-admin dashboard
18. Trial/subscription enforcement
19. DTO validation and consistent API errors
20. Tenant-isolation integration tests
21. Mongo indexes
22. Dockerized frontend/backend/MongoDB
23. Postman collection
24. Documentation

## Run locally

### Prerequisites
- Docker Desktop
- Java 25 (only needed if running backend outside Docker)
- Node 20+ (only needed if running Angular outside Docker)

### Start
```bash
docker compose up --build
```

Frontend: http://localhost:4200
Backend health: http://localhost:8080/actuator/health
MongoDB: mongodb://localhost:27017
Database: coaching_saas

### First login
1. Open http://localhost:4200
2. Start Free Trial
3. Create a coaching centre
4. Login with the owner email/password
5. Create teachers, batches and students from the dashboard.

### Super admin
For local development, configure:
```env
SUPER_ADMIN_EMAIL=superadmin@local.test
SUPER_ADMIN_PASSWORD=ChangeMe_123!
```
The initializer creates the account only if it does not already exist. Change these values for any shared environment.

## Local non-Docker development

Backend:
```bash
cd backend
./mvnw spring-boot:run
```

Frontend:
```bash
cd frontend
npm install
npm start
```

When Angular runs directly, set API URL in `frontend/src/environments/environment.ts`.

## MongoDB Atlas
Only the connection string needs to change:
```env
SPRING_DATA_MONGODB_URI=mongodb+srv://USER:PASSWORD@CLUSTER.mongodb.net/coaching_saas
```
Do not commit real credentials.

## Security notes
- Passwords are BCrypt hashed.
- Frontend never sends a trusted `coachingId`.
- Backend derives tenant from authenticated principal.
- Cross-tenant service queries always include `coachingId`.
- DTO validation rejects invalid input.
- Trial expiry blocks business operations while allowing authentication and super-admin management.
- Production should add HTTPS, secret management, rate limiting, audit logs, backups and monitoring.

See `/docs` and `/postman`.

## Current MVP workflows

Coaching admins can create subjects, teachers, batches, and students. Assign teachers to a batch using the teacher IDs when creating a batch, then create a class session for that assigned teacher. The teacher dashboard shows only assigned batches, active students, sessions, tests, and timetable entries.

Teacher attendance is session based. Select a class session, mark every active student as `PRESENT`, `ABSENT`, or `LATE`, and save once. The backend stores the complete class roster in one attendance document and rejects incomplete or cross-tenant submissions.

Authenticated users can change their password from Settings or `POST /api/auth/change-password` with `currentPassword` and `newPassword`. Invalid tokens are cleared by the Angular interceptor and redirected to login.

Management list APIs support server-side query parameters such as `search`, `batchId`, `page`, and `size` where applicable. Mongo business IDs are backed by persistent counters so Docker restarts do not reuse IDs.

## MongoDB Compass

Connect Compass to `mongodb://localhost:27017` and open the `coaching_saas` database. Main collections are `users`, `coaching_centres`, `students`, `teachers`, `subjects`, `batches`, `class_sessions`, `attendance`, `fee_structures`, `fee_payments`, `tests`, `test_results`, and `timetables`.

## Environment configuration

The Compose defaults are suitable only for local development. Configure `JWT_SECRET`, `SUPER_ADMIN_EMAIL`, `SUPER_ADMIN_PASSWORD`, and `CORS_ALLOWED_ORIGIN` through the environment for shared environments. Never commit production secrets.
