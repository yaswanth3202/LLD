package factory;

import enums.PaymentMode;
import strategy.payment.CashPayment;
import strategy.payment.PaymentStrategy;
import strategy.payment.UpiPayment;
import strategy.pricing.EventBasedPricing;
import strategy.pricing.PricingStrategy;
import strategy.pricing.TimeBasedPricing;

public class PaymentStrategyFactory
{
    public static PaymentStrategy produce(enums.PaymentMode mode)
    {
        return switch (mode)
        {
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
            case CARD -> new CashPayment();
        };
    }
}
