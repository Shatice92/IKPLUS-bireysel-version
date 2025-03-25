package org.hatice.ikplus.entity.companymanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.CompanyStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String emailDomain;
	private Long companyManagerId;
	private String Logo;
	@Enumerated(EnumType.STRING)
	private CompanyStatus status;
	private LocalDateTime applicationDate;
	private LocalDateTime updatedAt;
	private Long subscriptionId;
	private String address;
	private String phone;
	private Boolean emailVerified;
	
}