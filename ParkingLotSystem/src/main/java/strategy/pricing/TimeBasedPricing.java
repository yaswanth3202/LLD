package strategy.pricing;

import enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeBasedPricing implements PricingStrategy
{
    private LocalTime PEAK_START = LocalTime.of(8,0);
    private LocalTime PEAK_END = LocalTime.of(17,0);
    private boolean isPeak(LocalTime time)
    {
        return !PEAK_START.isBefore(time) && !PEAK_END.isAfter(time);
    }
    @Override
    public double calcFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime)
    {
        if(exitTime.isBefore(entryTime)) throw new IllegalArgumentException("Exit time should'nt be before entry time");
        long durationMinutes = Duration.between(entryTime,exitTime).toMinutes();
        long totalHours = (long)Math.ceil(durationMinutes / 60.0);

        LocalDateTime cursor = entryTime.truncatedTo(ChronoUnit.HOURS);

        int peakHours = 0;
        int nonPeakHours = 0;
        for(int i=0;i<totalHours;i++)
        {
            LocalTime hourStart = cursor.toLocalTime();
            if(isPeak(hourStart)) peakHours++;
            else nonPeakHours++;
            cursor = cursor.plusHours(1);
        }


        double peakRate = switch (type) {
            case CAR -> 30.0;
            case BIKE -> 15.0;
            case TRUCK -> 50.0;
        };

        double nonPeakRate = switch (type) {
            case CAR -> 20.0;
            case BIKE -> 10.0;
            case TRUCK -> 30.0;
        };

        return peakHours * peakRate + nonPeakHours*nonPeakRate;
    }
}
