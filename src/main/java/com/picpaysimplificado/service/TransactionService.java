package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.model.transaction.Transaction;
import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.repository.TransactionRepository;
import com.picpaysimplificado.shared.TransactionDTO;

@Service

public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private UserService userService;

  private RestTemplate restTemplate; // class to use requests for other apis

  @Autowired
  private NotificationService notificationService;

  public Transaction createTransaction(TransactionDTO transactionDto) throws Exception {
    User sender = userService.findUserById(transactionDto.senderId());
    User receiver = userService.findUserById(transactionDto.receiverId());

    userService.validateTransaction(sender, transactionDto.value());

    // if (!authorizeTransaction(sender, transactionDto.value())) {
    // throw new Exception("Transaction invalid.");
    // }

    Transaction transaction = new Transaction(null, transactionDto.value(), sender, receiver, LocalDateTime.now());
    transaction = transactionRepository.save(transaction);
    sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));
    receiver.setBalance(receiver.getBalance().add(transaction.getAmount()));
    userService.saveUsers(receiver);
    userService.saveUsers(sender);
    notificationService.sendNotification(sender, "transaction has been sucessfull.");
    notificationService.sendNotification(receiver, "transaction received.");
    return transaction;
  }

  public boolean authorizeTransaction(User sender, BigDecimal value) {
    ResponseEntity<Map> authorizationResponse = restTemplate
        .getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

    if (authorizationResponse.getStatusCode()
        .equals(HttpStatus.OK)) {

      return true;

    }
    return false;
  }

}
