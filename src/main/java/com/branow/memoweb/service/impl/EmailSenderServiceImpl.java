package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.email.EmailDto;
import com.branow.memoweb.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender sender;


    @Override
    public void send(EmailDto email) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email.getReceiver());
        helper.setFrom("MemoWebFlashcards");
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);
        sender.send(message);
    }

}
