package models;

import enums.PaymentStatus;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Ticket
{
    private final String id;
    private PaymentStatus status = PaymentStatus.PENDING;
    private final LocalDateTime entryTime;
    private String spotId;
}
