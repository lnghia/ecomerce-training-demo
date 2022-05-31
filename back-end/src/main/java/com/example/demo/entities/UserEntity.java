package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
public class UserEntity extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String username;

  private String password;

  private String email;

  private String avatar;

  private String otp;

  private String firstName;

  private String lastName;

  private Date lastSentOTPTime;

  private boolean isDeleted = false;

  private Boolean isActive = true;

  public void setEmail(String email) {
    this.email = email;
    this.username = email;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
  private Set<RoleEntity> roles;
}
