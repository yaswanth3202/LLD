package models;

import enums.GateType;
import enums.PaymentMode;
import services.ParkingLot;

import java.time.LocalDateTime;

public class ExitGate extends Gate
{
    public ExitGate(String id)
    {
        super(id);
    }
    @Override
    public GateType getType()
    {
        return GateType.EXIT_GATE;
    }
    public void unparkVehicle(Ticket ticket, LocalDateTime exitTime, PaymentMode paymentMode)
    {
        ParkingLot.getInstance().unParkVehicle(ticket,exitTime,paymentMode);
    }
}
