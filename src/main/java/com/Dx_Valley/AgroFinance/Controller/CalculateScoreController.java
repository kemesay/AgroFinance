package com.Dx_Valley.AgroFinance.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Dx_Valley.AgroFinance.DTO.AssetRequest;
import com.Dx_Valley.AgroFinance.DTO.AssetWithStatusRequest;
import com.Dx_Valley.AgroFinance.DTO.ScoreRequest;
import com.Dx_Valley.AgroFinance.Models.Asset;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import com.Dx_Valley.AgroFinance.Models.Education;
import com.Dx_Valley.AgroFinance.Models.FarmerAge;
import com.Dx_Valley.AgroFinance.Repository.AssetRepository;
import com.Dx_Valley.AgroFinance.Repository.AssetWithStatusRepository;
import com.Dx_Valley.AgroFinance.Repository.EducationRepository;
import com.Dx_Valley.AgroFinance.Repository.FarmerAgeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/score")
public class CalculateScoreController {
    private final AssetRepository assetRepository;
    private final AssetWithStatusRepository assetWithStatusRepository;
    private final FarmerAgeRepository farmerAgeRepository;
    private final EducationRepository educationRepository;

    // note create different table for registaring weight for each assets

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
        for (AssetWithStatusRequest assetWithStatausRequest : request.getAssetsWithStatus()) {
            if (assetWithStatausRequest.getAssetValue().equals("yes")) {
                AssetWithStatus assetWithStatus = assetWithStatusRepository
                        .findByStatusName(assetWithStatausRequest.getAssetName());

                if (assetWithStatus != null) {
                    totalScore += assetWithStatus.getStatusWeight();
                }
            }
        }

        // handle age case
        Double age = request.getAge();
        Double val;
        FarmerAge farmerAge = farmerAgeRepository
                .findByAgeIntervalStartLessThanEqualAndAgeIntervalEndGreaterThanEqual(age, age);
        if (farmerAge != null) {
            val = farmerAge.getAgeValue();
            Double weight = farmerAge.getWeight();
            Double normalizedValue = val / farmerAge.getAgeStandard();

            totalScore += normalizedValue * weight;
        } else {
            val = 1D;
            FarmerAge farmerAge2 = farmerAgeRepository.findById(1L).orElseThrow();
            Double weight = farmerAge2.getWeight();
            Double normalizedValue = val / farmerAge2.getAgeStandard();

            totalScore += normalizedValue * weight;
        }


         // Handle education scoring
        Education education = educationRepository.findByLevel(request.getEducationLevel());
        if (education != null) {
            Double weight = education.getWeight();
            Double normalizedValue = education.getScoreValue() / education.getStandard();

            totalScore += normalizedValue * weight;
        }

        return ResponseEntity.ok(totalScore);
    }

}
