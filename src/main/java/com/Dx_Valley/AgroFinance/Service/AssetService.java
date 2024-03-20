package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.Models.Asset;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssetService {

    List<Asset> fetchAsset();
    Asset editAsset (Asset asset);

    ResponseEntity<?> registerAsset(Asset asset);
}



