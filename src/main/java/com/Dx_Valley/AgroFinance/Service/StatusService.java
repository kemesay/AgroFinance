package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.Models.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatusService {
    List<Status> fetchStatus();
    Status editStatus (Status status);
    ResponseEntity<?> registerStatus(Status status);

}



