package services;

import enums.PaymentMode;
import enums.PaymentStatus;
import models.Ticket;
import strategy.payment.PaymentStrategy;

public class PaymentService
{
    public boolean processPayment(PaymentStrategy paymentStrategy,Ticket ticket,double amount)
    {
        boolean status = paymentStrategy.processPayment(ticket,amount);
        if(status) ticket.setStatus(PaymentStatus.SUCCESS);
        else ticket.setStatus(PaymentStatus.FAILED);
        return status;
    }
}
