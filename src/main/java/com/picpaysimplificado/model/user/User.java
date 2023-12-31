package com.picpaysimplificado.model.user;

import java.math.BigDecimal;

import com.picpaysimplificado.shared.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity(name = "users")
@Table(name = "users")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String document;
  @Column(unique = true)
  private String email;
  private String password;
  private BigDecimal balance;
  @Enumerated(EnumType.STRING)
  private UserType userType;

  public User(UserDTO userDto) {
    this.balance = userDto.balance();
    this.firstName = userDto.firstName();
    this.lastName = userDto.lastName();
    this.email = userDto.email();
    this.document = userDto.document();
    this.password = userDto.password();
    this.userType = userDto.userType();
  }

}
