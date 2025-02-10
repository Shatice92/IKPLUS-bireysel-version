package org.hatice.ikplus.entity.usermanagement;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserStatus status;
    private String role;
    private UserGender gender;
    private String phoneNumber;
    private Date birthDate;
    private UserMaritalStatus maritalStatus;
    private UserBloodType bloodType;
    private String identificationNumber;
    private String nationality;
    private UserEducationLevel educationLevel;
    private Long CreateAt;
    private Long UpdateAt;
}