# Courier-Delivery Service Design:
 ```
 Assume you are a courier company.
 How do you help your customers create delivery orders efficiently?
 ```

```
 Assume there are 3 types of customers.

 Customers that create:

  - 1 delivery every week
  - 10 deliveries a day
  - 1000 deliveries a day
```

```
 - Describe how you would solve this problem.
 - Walk us through your assumptions. No code needed.
```

## Solution Design:

### End-Product:

- Cloud-based courier management software for a single and multi-franchise business.
- Design workflow at macro and micro level of service delivery

   **Actors in the System:**

  > Franchise: MicroManagers to manage pickups, deliveries and collection points at multiple locations

        - manage courier collection by adhoc order or recurring schedule
        - deliver the in-coming couriers to customers
        - dispatch out-going couriers to collection-points at destinations
        - track the vehicles with couriers

  > Customer: Users who want to send and collect couriers

        - register via new ID creation or social login
        - Create a new adhoc schedule for courier pickup or self-service at nearest collection-point
        - Track the courier movement (in-App feature)
        - Receive Notifications and alerts about courier movement during entire workflow
        - Enter queries or complain about services
        - Rate the franchise or the courier service in general

   > Admin: Central authority to manage all franchises and operations

        - Has master access on all Franchises and Customer management
        - RealTime tracking of Franchises and collection points
        -

### Features:

#### Customer Perspective:

- Login / Registration by user / Social Login ( Facebook )
- Pickup & Destination By Type / Map
- Product Detail & Description
- Services by types
- Quantity
- Confirmation Of Products & Delivery Details
- Real-Time Tracking
- Review and rating
- Online Payment

#### Franchise Perspective:

- Login / Register / Social Login ( Facebook )
- Online Delivery Request with countdown
- In-app earning details
- ETA & Navigation
- Real-time customer location tracking
- Update Status (On the way, arrived etc)
- Document Submission
- Mass Push Notification
- Payment Detail
- Services Types
- Map View

#### Admin Perspective:

- Map View
- Services Types
- Counties and Cities
- Categories & Sub Categories
- Payment Detail
- Trips
- User, Drivers, Store Management
- Service History
- Mass Push Notification

## Tech-Stack:

- App to be designed for :
    - customers
    - Franchises
    - Admin (Operations and Support)

- MERN Stack:

  - Database is on mongoDB
  - Backend services on nodeJS and ExpressJS
  - Frontend on React-native to support iOS and Andriod
  - Web-Push for notifications and alerts

## Service Design:

- Design-Patterns:

  - SOLID Design principles
  - CQRS for micro-services
  - Event-Driven Services (Event as first-class citizen)
     - Eventual Consistency

### Customer Service components:

 1. **UserRegistrationService**
   - User registration
   - Set Customer preferences
   - Set customer base location
   - Upload KYC Documents
   - Set Business details (If Customer is a business entity)
   - Pre-configured Payment options
        1. Card
        2. Bank Account

 2. **OrderCaptureService**
    - __Accept Scheduled courier-pickup__
       - Schedule parameters
            1. Frequency
            2. Start-Date
            3. End-Date
            4. ServiceType
            5. Pickup Address & Coordinates
            5. Destination Address & Coordinates
    - __Cancellation__
        - Handle cancel recurring courier-pickup
     - __Amendment__
        - Amend recurring courier-pickup
        - Amend schedule


 3. **RatingService**
    - Rate the courier pickup service
    - Add review for the service

 4. **OrderBlotterService**
    - Blotter to show the active and past pickups
    - Track the Active-Orders with in blotter
    - View Order Details
    - View payment details for an order

 5. **TrackingService**
    - RealTime tracking for picked-up couriers
    - raise alerts and notifications for courier movement

 6. **PaymentService**
    - Initiate payment for scheduled pickups
        - Pickup event will initiate Payment
    - Pay for Adhoc Pickups
    - Topup Prepaid account for scheduled pickups


### Franchise Service components:

 1. **FranchiseOnboardingService**
   - User registration
   - Set Customer preferences
   - Set customer base location
   - Upload KYC Documents
   - Set Business details (If Customer is a business entity)
   - Pre-configured Payment options
        1. Card
        2. Bank Account

 2. **OrderService**
    - __Schedule courier-pickup__
       - Schedule parameters
            1. Frequency
            2. Start-Date
            3. End-Date
            4. ServiceType
            5. Pickup Address & Coordinates
            5. Destination Address & Coordinates
    - __Cancellation__
        - cancel recurring courier-pickup
        - delete entire scheduled pickups
        - delete remaining scheduled pickups
     - __Amendment__
        - Amend recurring courier-pickup
        - Amend schedule


 3. **RatingService**
    - Rate the courier pickup service
    - Add review for the service

 4. **OrderBlotterService**
    - Blotter to show the active and past pickups
    - Track the Active-Orders with in blotter
    - View Order Details
    - View payment details for an order

 5. **TrackingService**
    - RealTime tracking for picked-up couriers
    - raise alerts and notifications for courier movement

 6. **PaymentService**
    - Initiate payment for scheduled pickups
        - Pickup event will initiate Payment
    - Pay for Adhoc Pickups
    - Topup Prepaid account for scheduled pickups