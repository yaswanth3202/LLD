package strategy.payment;

import models.Ticket;

public class CardPayment implements PaymentStrategy
{
    @Override
    public boolean processPayment(Ticket ticket, double amount)
    {
        System.out.println("Paid ₹" + amount + " for ticket " + ticket.getId() + " via Card.");
        return true;
    }
}
