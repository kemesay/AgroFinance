package com.Dx_Valley.AgroFinance.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Dx_Valley.AgroFinance.DTO.AssetRequest;
import com.Dx_Valley.AgroFinance.DTO.AssetWithStatusRequest;
import com.Dx_Valley.AgroFinance.DTO.ScoreRequest;
import com.Dx_Valley.AgroFinance.Models.Asset;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import com.Dx_Valley.AgroFinance.Repository.AssetRepository;
import com.Dx_Valley.AgroFinance.Repository.AssetWithStatusRepository;
import com.Dx_Valley.AgroFinance.Service.AssetService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/score")
public class CalculateScoreController {
    private final AssetService assetService;
    private final AssetRepository assetRepository;
    private final AssetWithStatusRepository assetWithStatusRepository;

    @PostMapping("/calculate")
    private ResponseEntity<Double> calculateScore(@RequestBody ScoreRequest request) {
        Double totalScore = 0D;

        for (AssetRequest assetRequest : request.getAssets()) {
            Asset asset = assetRepository.findByAssetName(assetRequest.getAssetName());

            if (asset != null) {
                Double assetValue = assetRequest.getAssetValue();
                Double value = 0D;

                if (asset.getIsValueIncreasing()) {
                    value = Math.min(Math.ceil(assetValue / asset.getAssetIncrement()), asset.getAssetStandard());
                } else if (assetRequest.getAssetName().equals("FamilySize") && assetValue == 0) {
                    value = 0D;
                } else {
                    value = Math.max(asset.getAssetStartValue() - Math.floor(assetValue / asset.getAssetIncrement()),
                            asset.getAssetEndValue());
                }

                Double weight = asset.getAssetWeight();
                Double normalizedValue = value / asset.getAssetStandard();

                totalScore += normalizedValue * weight;
            }
        }

        // handle case for assets with status
        for(AssetWithStatusRequest assetWithStatausRequest : request.getAssetsWithStatus()){
            if(assetWithStatausRequest.getAssetValue().equals("yes")){
                AssetWithStatus assetWithStatus = assetWithStatusRepository.findByStatusName(assetWithStatausRequest.getAssetName());

                if(assetWithStatus != null){
                    totalScore+=assetWithStatus.getStatusWeight();
                }
            }
        }

        return ResponseEntity.ok(totalScore);
    }

}
