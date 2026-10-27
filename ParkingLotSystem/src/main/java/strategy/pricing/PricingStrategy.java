package strategy.pricing;

import enums.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy
{
    double calcFee(VehicleType type, LocalDateTime entryTime,LocalDateTime exitTime);
}
