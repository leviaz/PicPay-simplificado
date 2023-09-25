package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.model.user.UserType;
import com.picpaysimplificado.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public void validateTransaction(User sender, BigDecimal amount) throws Exception {
    if (sender.getUserType() == UserType.MERCHANT) {
      throw new Exception("Merchants cannot do the transfer operation.");
    }
    if (sender.getBalance().compareTo(amount) < 0) {
      throw new Exception("User's balance does not contain this value, the transfer should be not operated.");
    }
  }

  public User findUserById(Long id) throws Exception {
    Optional<User> userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      throw new Exception("Invalid Id");
    }
    User user = userOpt.get();
    return user;
  }

  public void addNewUsers(User user) {
    userRepository.save(user);
  }

}
