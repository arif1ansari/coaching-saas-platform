# API workflow

Base URL: `/api`

## Public
- `POST /auth/signup`
- `POST /auth/login`

## Admin
- `GET /dashboard`
- `POST /teachers`
- `GET /teachers`
- `PUT /teachers/{id}`
- `POST /batches`
- `GET /batches`
- `POST /students`
- `GET /students`
- `PUT /students/{id}`
- `POST /sessions`
- `GET /sessions`
- `POST /attendance/{sessionId}`
- `GET /attendance/report`
- `POST /fees`
- `GET /fees`
- `POST /fees/{feeId}/payments`
- `POST /tests`
- `GET /tests`
- `POST /tests/{testId}/results`
- `GET /tests/{testId}/results`
- `POST /timetable`
- `GET /timetable`

## Teacher
Teacher can authenticate, view assigned sessions/batches, mark attendance, create tests for assigned batches and submit results.

## Super admin
- `GET /super-admin/dashboard`
- `GET /super-admin/coaching-centres`
- `PATCH /super-admin/coaching-centres/{coachingId}/status`

All protected calls require:
`Authorization: Bearer <JWT>`
