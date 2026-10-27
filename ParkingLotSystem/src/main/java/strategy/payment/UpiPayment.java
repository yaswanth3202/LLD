package strategy.payment;

import models.Ticket;

public class UpiPayment implements PaymentStrategy
{
    @Override
    public boolean processPayment(Ticket ticket, double amount)
    {
        System.out.println("Paid ₹" + amount + " for ticket " + ticket.getId() + " via UPI.");
        return true;
    }
}
