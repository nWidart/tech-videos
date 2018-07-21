package com.nwidart.techvideo.service.email;

public interface EmailService {

  void sendText(String from, String to, String subject, String body);

  void sendHTML(String from, String to, String subject, String body);
}
