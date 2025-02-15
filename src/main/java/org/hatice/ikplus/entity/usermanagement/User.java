package org.hatice.ikplus.entity.usermanagement;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true,nullable = false)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	private Long roleId;
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	private String phoneNumber;
	private Date birthDate;
	@Enumerated(EnumType.STRING)
	private UserMaritalStatus maritalStatus;
	@Enumerated(EnumType.STRING)
	private UserBloodType bloodType;
	private String identificationNumber;
	private String nationality;
	@Enumerated(EnumType.STRING)
	private UserEducationLevel educationLevel;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long employeeId;
	@Column(nullable = false, unique = true, updatable = false)
	private UUID authId = UUID.randomUUID();  // Kullanıcının benzersiz authId'si
}