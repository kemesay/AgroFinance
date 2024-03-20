package com.Dx_Valley.AgroFinance.ServiceImp;
import com.Dx_Valley.AgroFinance.DTIO.ResponseMessage;
import com.Dx_Valley.AgroFinance.Models.Others;
import com.Dx_Valley.AgroFinance.Repository.OtherRepository;
import com.Dx_Valley.AgroFinance.Service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OtherServiceImp implements OtherService {
    @Autowired
    private OtherRepository otherRepository;
    @Override
    public ResponseEntity<?> registerOthers(Others others) {
        Others others1 = otherRepository.findByOtherName(others.getOtherName());
        ResponseMessage responseMessage;
        if (others1 == null) {
            otherRepository.save(others);
            responseMessage = new ResponseMessage("success", "Others created successfully");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage("fail", "Others already exist");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public Others editOthers(Others others) {
        return this.otherRepository.save(others);
    }

    @Override
    public List<Others> fetchOthers() {
        List<Others> others = new ArrayList<>();
        others.addAll(otherRepository.findAll());
        return others;
    }
}
