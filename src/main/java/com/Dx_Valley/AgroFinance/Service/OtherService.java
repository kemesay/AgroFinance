package com.Dx_Valley.AgroFinance.Service;
import com.Dx_Valley.AgroFinance.Models.Others;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OtherService {
    List<Others> fetchOthers();
    Others editOthers (Others others);

    ResponseEntity<?> registerOthers(Others others);

}



