package strategy.payment;

import models.Ticket;

public class CashPayment implements PaymentStrategy
{
    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid ₹" + amount + " for ticket " + ticket.getId() + " via Card.");
        return true;
    }
}