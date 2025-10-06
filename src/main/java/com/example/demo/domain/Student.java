package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Student {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email @NotBlank
  @Column(unique = true)
  private String email;

  // Getters/Setters
  public Long getId() { return id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}
