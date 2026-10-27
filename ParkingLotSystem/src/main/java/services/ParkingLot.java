package services;

import enums.PaymentMode;
import enums.PricingStrategyType;
import enums.VehicleType;
import factory.PaymentStrategyFactory;
import factory.PricingStrategyFactory;
import lombok.Setter;
import models.ParkingFloor;
import models.ParkingSpot;
import models.Ticket;
import models.Vehicle;
import strategy.payment.PaymentStrategy;
import strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot
{
    private static final ParkingLot INSTANCE = new ParkingLot();

    private final Map<String, ParkingFloor> floorMap = new HashMap<>();
    private final Map<String,Ticket> activeTickets = new HashMap<>();

    @Setter
    private PricingStrategy pricingStrategy = PricingStrategyFactory.produce(PricingStrategyType.TIME_BASED);

    public void addFloor(ParkingFloor floor)
    {
        floorMap.put(floor.getId(),floor);
    }
    public static ParkingLot getInstance()
    {
        return INSTANCE;
    }
    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime time)
    {
        while (true) {
            ParkingSpot spot = findAvailableSpot(vehicle.getType());
            if(spot!=null){
                if (spot.occupy()) {
                    String tid = UUID.randomUUID().toString();
                    Ticket ticket = Ticket.builder().entryTime(time).id(tid).spotId(spot.getId()).build();
                    activeTickets.put(tid, ticket);
                    System.out.println("Vehicle Parked: " + tid);
                    return ticket;
                }
            } else {
                return null;
            }
        }
    }
    public void unParkVehicle(Ticket ticket, LocalDateTime exitTime, PaymentMode paymentMode)
    {
        if (ticket == null) {
            System.out.println("Failed to unpark: Invalid or null ticket");
            return;
        }
        ParkingSpot spot = findSpot(ticket.getSpotId());
        if(spot==null) return;
        double toPay = pricingStrategy.calcFee(spot.getAllowedType(),ticket.getEntryTime(),exitTime);
        PaymentStrategy strategy = PaymentStrategyFactory.produce(paymentMode);
        if(!strategy.processPayment(ticket,toPay)) System.out.println("Payment unsuccessful cannot exit");
        spot.vacate();
        activeTickets.remove(ticket.getId());
        System.out.println("Vehicle Exited charged fee: "+toPay);
    }
    private ParkingSpot findAvailableSpot(VehicleType type)
    {
        for(ParkingFloor floor: floorMap.values())
        {
            ParkingSpot spot = floor.findSpot(type);
            if(spot!=null) return spot;
        }
        return null;
    }
    private ParkingSpot findSpot(String spotId)
    {
        for(ParkingFloor floor: floorMap.values())
        {
            ParkingSpot spot = floor.findSpot(spotId);
            if(spot!=null) return spot;
        }
        return null;
    }
    public void printStatus()
    {
        floorMap.forEach(
                (id,floor) -> {
                    System.out.println("Floor "+floor.getId());
                    floor.getSpots().values().forEach(
                            (spot) -> {
                                System.out.println("Spot "+spot.getId() + " ["+ spot.getAllowedType()+"] - "+ (spot.isOccupied() ? "Occupied" : "Free"));
                            }
                    );
                }
        );
    }
}
