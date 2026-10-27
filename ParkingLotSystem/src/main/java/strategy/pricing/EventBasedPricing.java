package strategy.pricing;

import enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class EventBasedPricing implements PricingStrategy
{
    private Map<VehicleType, Double> EVENT_HOURLY_RATES = Map.of(
        VehicleType.TRUCK,70.0,
        VehicleType.BIKE,30.0,
        VehicleType.CAR,50.0
    );

    @Override
    public double calcFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime)
    {
        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long hours = (long) Math.ceil(durationMinutes / 60.0); // always round up to next hour

        return EVENT_HOURLY_RATES.getOrDefault(type,0.0) * hours;
    }
}
