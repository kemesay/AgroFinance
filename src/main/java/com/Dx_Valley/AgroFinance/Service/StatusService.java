package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatusService {
    List<AssetWithStatus> fetchStatus();
    AssetWithStatus editStatus (AssetWithStatus status);
    ResponseEntity<?> registerStatus(AssetWithStatus status);

}



