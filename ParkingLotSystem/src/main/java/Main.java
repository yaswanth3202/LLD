import enums.PaymentMode;
import enums.PricingStrategyType;
import enums.VehicleType;
import factory.PricingStrategyFactory;
import factory.VehicleFactory;
import models.*;
import services.ParkingLot;
import utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        ParkingLot lot = ParkingLot.getInstance();
        EntryGate entryGate = new EntryGate(UUID.randomUUID().toString());
        ExitGate exitGate = new ExitGate(UUID.randomUUID().toString());

        lot.setPricingStrategy(PricingStrategyFactory.produce(PricingStrategyType.EVENT_BASED));

        ParkingFloor floor1 = new ParkingFloor("Floor1");
        floor1.putSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
        floor1.putSpot(new ParkingSpot("F1S2", VehicleType.BIKE));
        floor1.putSpot(new ParkingSpot("F1S3", VehicleType.TRUCK));
        floor1.putSpot(new ParkingSpot("F1S4", VehicleType.CAR));
        lot.addFloor(floor1);

        Vehicle bike1 = VehicleFactory.produce( VehicleType.BIKE,"KA01AB1234");
        Vehicle bike2 = VehicleFactory.produce( VehicleType.BIKE,"KA01AB5678");
        LocalDateTime entryTime = DateTimeParser.parse("21 May 7:30 AM 2025");
        System.out.println(entryTime.truncatedTo(ChronoUnit.HOURS));
        LocalDateTime exitTime1 = DateTimeParser.parse("21 May 8:30 PM 2025");
        Thread t1 = new Thread(() ->
            {

                Ticket tick = entryGate.parkVehicle(bike1, entryTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                exitGate.unparkVehicle(tick,exitTime1, PaymentMode.CASH);
            }
        );
        Thread t2 = new Thread(() ->
        {
            Ticket tick = entryGate.parkVehicle(bike2, entryTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            exitGate.unparkVehicle(tick,exitTime1, PaymentMode.CASH);
        }
        );

        t1.start();
        t2.start();


    }
}
