package org.hatice.ikplus.controller.usermanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.userrequest.SaveRoleRequestDto;
import org.hatice.ikplus.dto.request.userrequest.UpdateRoleRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.userresponse.RoleResponse;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.service.usermanagement.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping(Endpoints.ROLE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {
	private final RoleService roleService;
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> save(@RequestBody SaveRoleRequestDto dto) {
		roleService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true)
		                                     .message("Role saved successfully.").success(true).build());
	}
	
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<RoleResponse>>> getAllRoles() {
		List<RoleResponse> roleResponses = roleService.getAllRoles();
		
		return ResponseEntity.ok(
				BaseResponse.<List<RoleResponse>>builder()
				            .code(200)
				            .data(roleResponses)
				            .message("Roles listed successfully.")
				            .success(true)
				            .build()
		);
	}
	
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<RoleResponse>> updateRole(@PathVariable Long id,
	                                                        @RequestBody UpdateRoleRequestDto requestDto) {
		RoleResponse updatedRole = roleService.updateRole(id, requestDto);
		
		if (updatedRole == null) {
			return ResponseEntity.ok(
					BaseResponse.<RoleResponse>builder()
					            .code(400)
					            .data(null)
					            .message("Role not found.")
					            .success(false)
					            .build()
			);
		}
		
		return ResponseEntity.ok(
				BaseResponse.<RoleResponse>builder()
				            .code(200)
				            .data(updatedRole)
				            .message("Role updated successfully.")
				            .success(true)
				            .build()
		);
	}
	
	@GetMapping(FINDBYID)
	public ResponseEntity<BaseResponse<RoleResponse>> getRoleById(@PathVariable Long id) {
		// 1. Adım: RoleService'yi kullanarak rolü alıyoruz
		Role role = roleService.findById(id);
		
		// 2. Adım: Eğer rol bulunamazsa, hata mesajı döndürüyoruz
		if (role == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			                     .body(BaseResponse.<RoleResponse>builder()
			                                       .code(404)
			                                       .data(null)
			                                       .message("Rol bulunamadı.")
			                                       .success(false)
			                                       .build());
		}
		
		// 3. Adım: Bulunan rolü RoleResponse ile döndürüyoruz
		RoleResponse roleResponse = new RoleResponse(role.getName(), role.getPermissions());
		
		// 4. Adım: Başarılı response ile döndürüyoruz
		return ResponseEntity.ok(BaseResponse.<RoleResponse>builder()
		                                     .code(200)
		                                     .data(roleResponse)
		                                     .message("Rol başarıyla getirildi.")
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping(GETBYROLENAME)
	public ResponseEntity<BaseResponse<Long>> getRoleIdByName(@RequestParam RoleName roleName) {
		// Role ID'sini RoleService üzerinden alıyoruz
		Long roleId = roleService.findRoleIdByName(roleName);
		
		// Rol ID'si ile başarılı bir response dönüyoruz
		return ResponseEntity.ok(BaseResponse.<Long>builder()
		                                     .code(200)
		                                     .message("Role ID successfully fetched.")
		                                     .data(roleId)
		                                     .success(true)
		                                     .build());
	}
	
	
	
	
}