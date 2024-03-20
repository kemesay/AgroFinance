package com.Dx_Valley.AgroFinance.Controller;
import com.Dx_Valley.AgroFinance.DTIO.createUserResponse;
import com.Dx_Valley.AgroFinance.Models.Asset;
import com.Dx_Valley.AgroFinance.Repository.AssetRepository;
import com.Dx_Valley.AgroFinance.Service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @Autowired
    private AssetRepository assetRepository;
    @PostMapping("/registerAsset")
    public ResponseEntity<?> createAsset(@RequestBody Asset asset) {
        return  assetService.registerAsset(asset);

    }

    @GetMapping("/getAssets")
    private ResponseEntity<?> fetchOthers() {
        List<Asset> asset = assetService.fetchAsset();
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }


    @GetMapping("/getAsset/{assetId}")
    public ResponseEntity<?> getByAssetId(@PathVariable Long assetId) {
        var asset = assetRepository.findByAssetId(assetId);
        if (asset == null) {
            createUserResponse response = new createUserResponse("error", "Cannot find this asset!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }
    @PutMapping("/edit/{assetId}")
    Asset editAsset(@RequestBody Asset asset, @PathVariable Long assetId) {
        Asset asset1 = this.assetRepository.findByAssetId(assetId);
        asset1.setAssetName(asset.getAssetName());
        asset1.setAssetDescription(asset.getAssetDescription());
        asset1.setAssetWeight(asset.getAssetWeight());
        asset1.setAssetIncrement(asset.getAssetIncrement());
        asset1.setAsset_intervalStart(asset.getAsset_intervalStart());
        asset1.setAsset_intervalEnd(asset.getAsset_intervalEnd());
        asset1.setAsset_startValue(asset.getAsset_startValue());
        asset1.setAsset_endValue(asset.getAsset_endValue());
        return assetService.editAsset(asset1);
    }
    @DeleteMapping("/delete/others/{otherId}")
    void deleteOthers(@PathVariable Long assetId) {
        this.assetRepository.deleteById(assetId);
    }


}
