package org.hatice.ikplus.service.usermanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.usermanagement.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
