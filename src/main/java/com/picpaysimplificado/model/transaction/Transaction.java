package com.picpaysimplificado.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpaysimplificado.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal amount;
  // Notation to indicates that one transaction can have only one sender and
  // receiver
  // in other hand, an user can have many transactions
  @ManyToOne
  @JoinColumn(name = "receiver_id") // foreign key
  private User sender;
  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User receiver;
  private LocalDateTime timestamp;
}