package com.Dx_Valley.AgroFinance.ServiceImp;

import com.Dx_Valley.AgroFinance.DTO.ResponseMessage;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import com.Dx_Valley.AgroFinance.Repository.AssetWithStatusRepository;
import com.Dx_Valley.AgroFinance.Service.AssetWithStatusService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AssetWithStatusServiceImp implements AssetWithStatusService {

    private final AssetWithStatusRepository statusRepository;

    @Override
    public ResponseEntity<?> registerAssetWithStatus(AssetWithStatus status) {
        AssetWithStatus status1 = statusRepository.findByStatusName(status.getStatusName());
        ResponseMessage responseMessage;
        if (status1 == null) {
            statusRepository.save(status);
            responseMessage = new ResponseMessage("success", "Status created successfully");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage("fail", "status already exist");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public List<AssetWithStatus> fetchAssetWithStatus() {
        List<AssetWithStatus> status = new ArrayList<>();
        status.addAll(statusRepository.findAll());
        return status;
    }
}
