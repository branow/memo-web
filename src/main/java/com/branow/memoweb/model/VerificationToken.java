package com.branow.memoweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VerificationToken {

    private static int EXPIRATION_TIME = 10;

    private static LocalDateTime calcExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }

    @Id
    private Integer userId;

    private String token;

    private LocalDateTime expirationTime;

    @MapsId
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


    @PrePersist
    private void setExpirationTime() {
        expirationTime = calcExpirationTime();
    }

}
