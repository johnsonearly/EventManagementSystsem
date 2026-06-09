# Event Booking RESTful API

A robust backend-only RESTful API built with Spring Boot for managing events and automating seat reservation workflows. This system enforces strict business validation rules for capacities, duplicate bookings, and event availability.

---

##  Tech Stack

* **Backend Framework:** Spring Boot 3.x / 4.x
* **Language:** Java 17+
* **Build Tool:** Maven (or Gradle)
* **Database:** H2 Database (In-Memory / Persistent)
* **ORM / JPA:** Spring Data JPA / Hibernate
* **API Documentation:** Springdoc OpenAPI v3 (OAS 3.0 configuration)

---

## Features & Functional Requirements

### 1. Event Management
* **Create Events:** Set localized titles, descriptions, future-only dates, venues, and a fixed capacity (`totalSeats`).
* **Retrieve Events:** Fetch global event listings or extract granular detail payloads for a single event using its unique `UUID`.

### 2. Booking Workflows
* **Seat Reservation:** Instantly claim seats for real-time events.
* **Cancellation Engine:** Relinquish a booking, automatically decrementing the event's `bookedSeats` ledger to open availability for other attendees.

###  Core Business Rules & Validations
* **Chronological Guardrails:** Events can only be registered with a strict future date constraint.
* **Capacity Control:** Submitting a booking request against an event marked as `CLOSED` or matching `bookedSeats >= totalSeats` throws a `400 Bad Request` exception.
* **Idempotency / Anti-Duplicate Check:** The system prevents the same email address from registering for the same unique event id multiple times.
* **Data Integrity:** Validates email formatting, ensures `totalSeats > 0`, and securely tracks state changes across transactional boundaries.

---

##  API Endpoint Specification

### Event Routes
| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| **POST** | `/events` | Creates a new event setup | `201 Created`, `400` |
| **GET** | `/events` | Lists all active and closed events | `200 OK` |
| **GET** | `/events/{id}` | Fetches detailed model data of an event | `200 OK`, `404` |

### Booking Routes
| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| **POST** | `/events/{id}/bookings` | Claims a seat for an attendee at a specific event | `201 Created`, `400`, `404` |
| **GET** | `/events/{id}/bookings` | Lists all booking records tied to a single event | `200 OK`, `404` |
| **DELETE** | `/bookings/{id}` | Cancels an active reservation and frees up a seat | `200 OK`, `404` |

---

##  Getting Started: How to Build and Run

### Prerequisites
* Ensure **Java 17** or higher is installed (`java -version`).
* Ensure **Maven 3.x** is installed (`mvn -version`).

### Step 1: Clone and Navigate
```bash
git clone <your-repository-url>
cd event-booking-system
