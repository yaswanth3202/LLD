package strategy.payment;

import models.Ticket;

public interface PaymentStrategy
{
    boolean processPayment(Ticket ticket,double amount);


}
