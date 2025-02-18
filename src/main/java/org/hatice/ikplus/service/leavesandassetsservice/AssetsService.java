package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.leavesandassetrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateAssetRequestDto;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.AssetResponseDto;
import org.hatice.ikplus.entity.leaveandassetmanagement.Assets;
import org.hatice.ikplus.enums.leaveandassetenums.AssetStatus;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.AssetMapper;
import org.hatice.ikplus.repository.leaveandassetrepository.AssetsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetsService {
	private final AssetsRepository assetsRepository;
	public void save(CreateAssetRequestDto dto) {
		Assets asset = AssetMapper.INSTANCE.fromCreateAssetRequestDto(dto);
		assetsRepository.save(asset);
	}
	
	public AssetResponseDto updateAsset(Long id, UpdateAssetRequestDto dto) {
		Assets existingAsset = assetsRepository.findById(id)
		                                       .orElseThrow(() -> new IKPlusException(ErrorType.ASSET_NOT_FOUND));
		
		AssetMapper.INSTANCE.updateAssetFromDto(dto, existingAsset);
		Assets updatedAsset = assetsRepository.save(existingAsset);
		
		return AssetMapper.INSTANCE.toAssetResponseDto(updatedAsset);
	}
	
	public AssetResponseDto findById(Long id) {
		return assetsRepository.findById(id)
		                       .map(AssetMapper.INSTANCE::toAssetResponseDto)
		                       .orElseThrow(() -> new IKPlusException(ErrorType.ASSET_NOT_FOUND));
	}
	
	public List<AssetResponseDto> findAll() {
		return assetsRepository.findAll()
		                       .stream()
		                       .map(AssetMapper.INSTANCE::toAssetResponseDto)
		                       .collect(Collectors.toList());
	}
	
	public List<AssetResponseDto> getAssetsByEmployeeId(Long employeeId) {
		List<Assets> assets = assetsRepository.findByEmployeeId(employeeId);
		if (assets.isEmpty()) {
			throw new IKPlusException(ErrorType.ASSET_NOT_FOUND);
		}
		return assets.stream()
		             .map(AssetMapper.INSTANCE::toAssetResponseDto)
		             .collect(Collectors.toList());
	}
	
	public void updateStatus(Long id, AssetStatus status) {
		Assets asset = assetsRepository.findById(id)
		                               .orElseThrow(() -> new IKPlusException(ErrorType.ASSET_NOT_FOUND));
		
		asset.setStatus(status);
		assetsRepository.save(asset);
	}
	
	public void delete(Long id) {
		Assets asset = assetsRepository.findById(id)
		                               .orElseThrow(() -> new IKPlusException(ErrorType.ASSET_NOT_FOUND));
		assetsRepository.delete(asset);
	}
	
	public void updateAssetStatus(Long id, AssetStatus status) {
		Assets asset = assetsRepository.findById(id)
		                               .orElseThrow(() -> new IKPlusException(ErrorType.ASSET_NOT_FOUND));
		
		asset.setStatus(status);
		assetsRepository.save(asset);
	}
}