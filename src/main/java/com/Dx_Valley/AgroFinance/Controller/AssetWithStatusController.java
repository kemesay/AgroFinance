package com.Dx_Valley.AgroFinance.Controller;

import com.Dx_Valley.AgroFinance.DTO.createUserResponse;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import com.Dx_Valley.AgroFinance.Repository.AssetWithStatusRepository;
import com.Dx_Valley.AgroFinance.Service.AssetWithStatusService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assetsWithStatus")
public class AssetWithStatusController {

    private final AssetWithStatusService assetService;
    private final AssetWithStatusRepository assetRepository;

    @PostMapping
    public ResponseEntity<?> createAsset(@RequestBody AssetWithStatus asset) {
        return assetService.registerAssetWithStatus(asset);

    }

    @GetMapping
    private ResponseEntity<?> fetchOthers() {
        List<AssetWithStatus> asset = assetService.fetchAssetWithStatus();
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<?> getByAssetId(@PathVariable Long assetId) {
        var asset = assetRepository.findById(assetId);
        if (asset == null) {
            createUserResponse response = new createUserResponse("error", "Cannot find this asset!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{otherId}")
    void deleteOthers(@PathVariable Long assetId) {
        assetRepository.deleteById(assetId);
    }

}
