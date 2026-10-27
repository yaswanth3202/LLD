package models;

import enums.GateType;
import lombok.RequiredArgsConstructor;
import services.ParkingLot;

import java.time.LocalDateTime;
public class EntryGate extends Gate
{
    public EntryGate(String id)
    {
        super(id);
    }
    @Override
    public GateType getType()
    {
        return GateType.ENTRY_GATE;
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime time)
    {
        return ParkingLot.getInstance().parkVehicle(vehicle,time);
    }
}
