package models;


import enums.VehicleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@RequiredArgsConstructor
public class ParkingSpot
{
    private final String id;
    private final VehicleType allowedType;
    private AtomicBoolean isOccupied = new AtomicBoolean(false);

    public boolean occupy()
    {
        return isOccupied.compareAndSet(false,true);
    }

    public void vacate()
    {
        isOccupied.set(false);
    }

    public boolean isOccupied()
    {
        return isOccupied.get();
    }
}
