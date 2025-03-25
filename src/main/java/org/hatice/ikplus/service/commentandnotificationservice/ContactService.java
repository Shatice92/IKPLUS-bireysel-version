package org.hatice.ikplus.service.commentandnotificationservice;


import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.commentandnotificationrequest.ContactRequestDto;
import org.hatice.ikplus.mapper.ContactMapper;
import org.hatice.ikplus.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
	private final ContactRepository contactRepository;
	
	
	public void save(ContactRequestDto dto) {
		contactRepository.save(ContactMapper.INSTANCE.fromContactRequestDto(dto));
	}
	
	
}