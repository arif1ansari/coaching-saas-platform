# Architecture

```text
Browser
  |
  v
Angular 20
  |
  | /api/*
  v
Nginx reverse proxy
  |
  v
Spring Boot API
  |
  +-- JWT authentication
  +-- Role authorization
  +-- Tenant context from JWT
  +-- DTO validation
  +-- Trial/subscription guard
  |
  v
MongoDB
```

## Tenant rule

Every coaching-owned document contains `coachingId`.

The frontend never decides which tenant is accessed. The backend gets the authenticated user's `coachingId` from the JWT and applies it to every repository query.

`SUPER_ADMIN` is the only role allowed to operate across tenants.

## Roles

| Role | Scope |
|---|---|
| SUPER_ADMIN | all coaching centres |
| COACHING_ADMIN | one coaching centre |
| TEACHER | one coaching centre + assigned batches |

## Attendance

`class_sessions` stores the class event. `attendance` stores one attendance document per session with student statuses embedded.

## Fees

`fee_structures` stores the student's configured fee/installments. `fee_payments` stores each payment transaction. Outstanding balance is calculated from the structure and payments.

## Tests

`tests` stores test definitions. `test_results` stores student marks for a test.

## Subscription

A coaching centre starts on TRIAL. The API checks status/end date for business operations. Super-admin endpoints remain available.
