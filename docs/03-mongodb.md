# MongoDB collections

- `coaching_centres`: tenant/company subscription data
- `users`: authentication and roles
- `students`: student profiles
- `teachers`: teacher profiles
- `batches`: batch/class groups
- `subjects`: subjects
- `class_sessions`: scheduled/actual classes
- `attendance`: attendance per session
- `fee_structures`: student fee plans
- `fee_payments`: payment transactions
- `tests`: test definitions
- `test_results`: marks/results
- `timetables`: recurring timetable entries

All tenant-owned collections include `coachingId`.

Important indexes:
- users: unique email
- coaching centres: unique coachingId
- tenant + business IDs for common lookups
- tenant + date/session/batch for attendance
