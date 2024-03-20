package com.Dx_Valley.AgroFinance.ServiceImp;
import com.Dx_Valley.AgroFinance.DTIO.ResponseMessage;
import com.Dx_Valley.AgroFinance.Models.Status;
import com.Dx_Valley.AgroFinance.Repository.StatusRepository;
import com.Dx_Valley.AgroFinance.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class StatusServiceImp implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public ResponseEntity<?> registerStatus(Status status) {
        Status status1 = statusRepository.findByStatusName(status.getStatusName());
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
    public Status editStatus(Status status) {
        return this.statusRepository.save(status);
    }

    @Override
    public List<Status> fetchStatus() {
        List<Status> status = new ArrayList<>();
        status.addAll(statusRepository.findAll());
        return status;
}
}
