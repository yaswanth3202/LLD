package models;


import enums.VehicleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ParkingFloor
{
    private final String id;
    private final Map<String,ParkingSpot> spots = new HashMap<>();
    public void putSpot(ParkingSpot spot)
    {
        spots.put(spot.getId(),spot);
    }
    public ParkingSpot findSpot(VehicleType type)
    {
        for(ParkingSpot spot: spots.values())
        {
            if(!spot.isOccupied() && spot.getAllowedType() == type) return spot;
        }
        return null;
    }
    public ParkingSpot findSpot(String id) {return spots.getOrDefault(id,null);}
}
