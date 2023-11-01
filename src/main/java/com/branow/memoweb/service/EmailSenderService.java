package com.branow.memoweb.service;

import com.branow.memoweb.dto.email.EmailDto;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {

    void send(EmailDto email) throws MessagingException;

}
