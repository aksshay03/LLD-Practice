# Parking Lot - Low Level Design

A Java implementation of a Parking Lot system designed using Object-Oriented Design, SOLID principles, and the Strategy Design Pattern.

## Requirements

The parking lot should support:

- Multiple parking floors
- Different types of parking spots
- Different vehicle types
- Parking and unparking vehicles
- Ticket generation
- Parking fee calculation
- Pluggable spot assignment strategies

### Vehicle Types

- Bike
- Car
- Truck

### Spot Types

- Bike
- Car
- Truck

## Design

The system is divided into classes with focused responsibilities.

### Main Classes

| Class | Responsibility |
|---|---|
| `ParkingLot` | Coordinates parking and unparking |
| `ParkingFloor` | Represents a parking floor |
| `ParkingSpotManager` | Manages parking spots and delegates spot assignment |
| `ParkingSpot` | Maintains spot state and vehicle compatibility |
| `Vehicle` | Represents a vehicle |
| `Ticket` | Represents a parking session |
| `TicketService` | Creates and closes tickets |
| `FeeCalculator` | Calculates parking fees |
| `SpotAssignmentStrategy` | Defines the spot assignment algorithm |
| `FirstAvailableSpotStrategy` | Assigns the first available compatible spot |

## Class Diagram

![Parking Lot Class Diagram](class-diagram.png)

## Sequence Diagram

![Parking Lot Sequence Diagram](sequence-diagram.png)

## Design Patterns

### Strategy Pattern

The Strategy Pattern is used for parking spot assignment.

```text
SpotAssignmentStrategy
        ▲
        │
        │ implements
        │
FirstAvailableSpotStrategy
```

`ParkingSpotManager` depends on the `SpotAssignmentStrategy` interface rather than a concrete implementation.

This allows different assignment strategies to be added without modifying `ParkingSpotManager`.

For example:

- First available spot
- Nearest spot
- Cheapest spot
- Preferred spot

## SOLID Principles

### Single Responsibility Principle

Each class has a focused responsibility.

For example:

- `TicketService` handles ticket lifecycle.
- `FeeCalculator` handles fee calculation.
- `ParkingSpot` handles parking spot state.
- `ParkingLot` coordinates the overall parking operation.

### Open/Closed Principle

New parking spot assignment strategies can be added without modifying `ParkingSpotManager`.

For example, a `NearestSpotStrategy` can be added by implementing `SpotAssignmentStrategy`.

### Dependency Inversion Principle

`ParkingSpotManager` depends on the abstraction:

`SpotAssignmentStrategy`

instead of directly depending on:

`FirstAvailableSpotStrategy`

The strategy is injected through the constructor.

## Parking Flow

```text
Vehicle arrives
      ↓
ParkingLot.parkVehicle()
      ↓
ParkingFloor.findSpot()
      ↓
ParkingSpotManager.findSpot()
      ↓
SpotAssignmentStrategy
      ↓
ParkingSpot.canFit()
      ↓
ParkingSpot.occupy()
      ↓
TicketService.createTicket()
      ↓
Ticket returned
```

## Unparking Flow

```text
Ticket
  ↓
ParkingLot.unparkVehicle()
  ↓
Set exit time
  ↓
FeeCalculator.calculateFee()
  ↓
Release ParkingSpot
  ↓
Close Ticket
  ↓
Return parking fee
```

## Parking Fee

The current pricing model is:

- ₹20 per hour
- Partial hours are rounded up
- Minimum charge is ₹20

Examples:

| Parking Duration | Fee |
|---|---:|
| 30 minutes | ₹20 |
| 60 minutes | ₹20 |
| 61 minutes | ₹40 |
| 121 minutes | ₹60 |

## Example

```text
Car arrives
    ↓
Car-compatible spot found
    ↓
Spot occupied
    ↓
Ticket generated
    ↓
Car leaves
    ↓
Exit time recorded
    ↓
Fee calculated
    ↓
Spot released
    ↓
Ticket closed
```

## Project Structure

```text
parking-lot/
├── src/
│   └── parkinglot/
│       ├── Main.java
│       ├── Vehicle.java
│       ├── VehicleType.java
│       ├── ParkingSpot.java
│       ├── SpotType.java
│       ├── ParkingSpotManager.java
│       ├── ParkingFloor.java
│       ├── ParkingLot.java
│       ├── Ticket.java
│       ├── TicketService.java
│       ├── FeeCalculator.java
│       ├── SpotAssignmentStrategy.java
│       └── FirstAvailableSpotStrategy.java
│
├── class-diagram.png
├── sequence-diagram.png
└── README.md
```

## Future Extensions

The current implementation can be extended to support:

- Nearest spot assignment
- Different pricing strategies
- Electric vehicle charging spots
- Reservations
- Multiple entry and exit gates
- Payment processing
- Display boards showing available spots
- Database persistence
- Different pricing based on vehicle type