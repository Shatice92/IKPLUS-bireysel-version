package org.hatice.ikplus.entity.commentandnotificationmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userImageUrl;
	private Long companyId;
	private Long companyManagerId;
	private String comment;
	private Double rating;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}