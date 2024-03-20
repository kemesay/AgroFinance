package com.Dx_Valley.AgroFinance.ServiceImp;
import com.Dx_Valley.AgroFinance.DTIO.ResponseMessage;
import com.Dx_Valley.AgroFinance.Models.Asset;
import com.Dx_Valley.AgroFinance.Repository.AssetRepository;
import com.Dx_Valley.AgroFinance.Service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class AssetServiceImp implements AssetService {
    @Autowired
    private AssetRepository assetRepository;
    @Override
    public ResponseEntity<?> registerAsset(Asset asset) {
        Asset asset1 = assetRepository.findByAssetName(asset.getAssetName());
        ResponseMessage responseMessage;
        if (asset1 == null) {
            assetRepository.save(asset);
            responseMessage = new ResponseMessage("success", "asset created successfully");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage("fail", "asset already exist");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public Asset editAsset(Asset asset) {
        return this.assetRepository.save(asset);
    }

    @Override
    public List<Asset> fetchAsset() {
        List<Asset> asset = new ArrayList<>();
        asset.addAll(assetRepository.findAll());
        return asset;
    }
}
