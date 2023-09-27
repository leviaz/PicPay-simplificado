package com.picpaysimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.shared.NotificationDTO;

@Service
public class NotificationService {
  // @Autowired
  // private RestTemplate restTemplate;

  public void sendNotification(User user, String message) {
    String email = user.getEmail();
    NotificationDTO notificationRequest = new NotificationDTO(email, message);
    // ResponseEntity<String> notificationResponse =
    // restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",
    // notificationRequest, String.class);
    // if (!notificationResponse.getStatusCode().equals(HttpStatus.OK)) {
    // System.out.println("Error");
    // throw new Exception("Service unstable or offline");

    // }
    System.out.println("Notification sended.");

  }
}
