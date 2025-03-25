package org.hatice.ikplus.controller.commentandnotificationcontroller;


import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.commentandnotificationrequest.ContactRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.service.commentandnotificationservice.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(Endpoints.CONTACT)
@RequiredArgsConstructor
public class ContactController {
	
	private final ContactService contactService;
	
	
	@PostMapping(Endpoints.SAVE)
	public ResponseEntity<BaseResponse<Boolean>> saveContact(@RequestBody ContactRequestDto dto) {
		contactService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true)
		                                     .message("Contact message saved succesfully").success(true).build());
	}
}