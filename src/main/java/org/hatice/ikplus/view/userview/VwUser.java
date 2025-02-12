package org.hatice.ikplus.view.userview;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class VwUser {
	private String userName;
	private String lastName;
	private String email;
}