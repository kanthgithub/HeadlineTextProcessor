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

## Design:

### Solution Design:

#### End-Product:

- Cloud-based courier management software for a single and multi-franchise business.
- Design workflow at macro and micro level of service delivery

  - Franchise: collection points at multiple locations to :

    - manage courier collection by adhoc order or recurring schedule
    - deliver the in-coming couriers to customers
    - dispatch out-going couriers to collection-points at destinations
    - track the vehicles with couriers

  - Customer: self explanatory where users who want to send couriers can

    - register via new ID creation or social login
    - Create a new adhoc schedule for courier pickup or self-service at nearest collection-point
    - Track the courier movement (in-App feature)
    - Receive Notifications and alerts about courier movement during entire workflow
    - Enter queries or complain about services
    - Rate the franchise or the courier service in general

#### Features:

##### Customer Perspective:

- Login / Registration by user / Social Login ( Facebook )
- Pickup & Destination By Type / Map
- Product Detail & Description
- Services by types
- Quantity
- Confirmation Of Products & Delivery Details
- Real-Time Tracking
- Review and rating
- Online Payment

##### Franchise Perspective:

- Login / Register / Social Login ( Facebook )
- Online Delivery Request with countdown
- In-app earning details
- ETA & Navigation
- Real-time customer location tracking
- Rate and Review
- Update Status (On the way, arrived etc)
- Document Submission

## UI & UX:

App to be designed for :
- customers
- Franchises
- Operations and Support
