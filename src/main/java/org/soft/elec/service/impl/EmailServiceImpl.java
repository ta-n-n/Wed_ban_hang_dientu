package org.soft.elec.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.soft.elec.repository.UserRepository;
import org.soft.elec.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {

  @Autowired private JavaMailSender mailSender;

  @Autowired private UserRepository userRepository;

  @Autowired private TemplateEngine templateEngine;

  @Async
  @Override
  public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(htmlBody, true);
    helper.setFrom("phungvanvu0@gmail.com");
    mailSender.send(message);
  }
}
