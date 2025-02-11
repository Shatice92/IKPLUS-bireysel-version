package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.leaveandassetrepository.AssetsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetsService {
	private final AssetsRepository assetsRepository;
}