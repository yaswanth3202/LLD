# 🚗 Parking Lot System (Low-Level Design)

![Java](https://img.shields.io/badge/Java-14-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-red?style=for-the-badge)

A robust and scalable Object-Oriented Low-Level Design (LLD) implementation of a **Parking Lot System** built with Java. This project demonstrates core OOP principles and industry-standard design patterns to handle vehicle entries, parking spot allocations, pricing calculations, and exit payments.

## ✨ Core Features

- **Multi-Level Parking:** Supports multiple parking floors and various parking spots.
- **Vehicle Diversity:** Handles different vehicle types such as Bikes, Cars, and Trucks.
- **Dynamic Pricing Strategies:** Extensible pricing strategies for dynamic cost calculation (e.g., Time-based, Event-based).
- **Payment Processing:** Supports multiple payment modes including UPI, Card, and Cash.
- **Thread-Safe Operations:** Designed to handle concurrent entry and exit of vehicles using multithreading.

## 🏗️ Design Patterns Used

This project heavily leverages object-oriented design patterns to maintain clean, extensible, and decoupled code:

### 1. Strategy Pattern 
- **Pricing Strategy (`PricingStrategy`):** Decouples the pricing calculation logic from the core system. Includes implementations like `TimeBasedPricing` and `EventBasedPricing`.
- **Payment Strategy (`PaymentStrategy`):** Handles different modes of payment (`UpiPayment`, `CardPayment`, `CashPayment`) seamlessly without modifying the core payment service.

### 2. Factory Pattern 
- **`VehicleFactory`:** Encapsulates the creation logic of different vehicle entities (`Bike`, `Car`, `Truck`).
- **`PricingStrategyFactory`:** Dynamically provides the appropriate pricing strategy based on the system configuration.
- **`PaymentStrategyFactory`:** Resolves the correct payment handling mechanism based on the user's chosen payment mode.

### 3. Singleton Pattern 
- **`ParkingLot`:** Ensures there is only one centralized instance of the Parking Lot service managing the floors and spot allocations across the entire application runtime.

## 📂 Project Structure

```text
src/main/java/
├── enums/       # VehicleType, PaymentMode, PaymentStatus, GateType, PricingStrategyType
├── factory/     # PaymentStrategyFactory, PricingStrategyFactory, VehicleFactory
├── models/      # Bike, Car, Truck, Vehicle, Ticket, Gate, EntryGate, ExitGate, ParkingFloor, ParkingSpot
├── services/    # ParkingLot, PaymentService
├── strategy/    # Strategy interfaces and concrete implementations for Pricing and Payments
├── utils/       # Utility classes like DateTimeParser
└── Main.java    # Application entry point demonstrating multithreaded parking
```

## 🚀 Getting Started

### Prerequisites
- Java 14 or higher
- Apache Maven

### Installation & Setup

1. **Clone the repository** (if not already cloned) and navigate to the project root directory.
2. **Compile the project:**
   This project uses Lombok, so Maven will run the annotation processor during the build.
   ```bash
   mvn clean install
   ```
3. **Run the Application:**
   Execute the `Main` class which simulates a real-world scenario with multiple vehicles entering and exiting concurrently.
   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ```

## 💡 Usage Example

The `Main.java` class demonstrates how the system operates:
- The `ParkingLot` singleton is initialized with a selected `PricingStrategy`.
- Floors and Spots are configured.
- Vehicles are created using `VehicleFactory`.
- Threads simulate simultaneous vehicle entries at the `EntryGate`, generating a `Ticket`.
- Vehicles exit through the `ExitGate`, calculating the fare and completing the transaction using a `PaymentStrategy`.

## 🛠️ Tech Stack
- **Language:** Java 14
- **Build Tool:** Apache Maven
- **Utilities:** Project Lombok (for boilerplate reduction like Getters/Setters/Constructors)
