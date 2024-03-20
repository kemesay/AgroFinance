package com.Dx_Valley.AgroFinance.Controller;
import com.Dx_Valley.AgroFinance.DTIO.createUserResponse;
import com.Dx_Valley.AgroFinance.Models.Others;
import com.Dx_Valley.AgroFinance.Repository.OtherRepository;
import com.Dx_Valley.AgroFinance.Service.OtherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/others")
public class OthersController {
    @Autowired
    private OtherService otherService;
    @Autowired
    private OtherRepository otherRepository;
    @PostMapping("/registerOthers")
    public ResponseEntity<?> createOthers(@RequestBody Others others) {
        return  otherService.registerOthers(others);

    }

    @GetMapping("/getOthers")
    private ResponseEntity<?> fetchOthers() {
        List<Others> others = otherService.fetchOthers();
        return new ResponseEntity<>(others, HttpStatus.OK);
    }


    @GetMapping("/getOthers/{otherId}")
    public ResponseEntity<?> getByOtherId(@PathVariable Long otherId) {
        var others = otherRepository.findByOtherId(otherId);
        if (others == null) {
            createUserResponse response = new createUserResponse("error", "Cannot find this Others!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(others, HttpStatus.OK);
    }
    @PutMapping("/edit/{otherId}")
    Others editOthers(@RequestBody Others others, @PathVariable Long otherId) {
        Others others1 = this.otherRepository.findByOtherId(otherId);
        others1.setOtherName(others.getOtherName());
        others1.setOtherDescription(others.getOtherDescription());
        others1.setOtherWeight(others.getOtherWeight());
        others1.setOtherIncrement(others.getOtherIncrement());
        others1.setOther_intervalStart(others.getOther_intervalStart());
        others1.setOther_intervalEnd(others.getOther_intervalEnd());
        others1.setOther_startValue(others.getOther_startValue());
        others1.setOther_endValue(others.getOther_endValue());
        return otherService.editOthers(others1);
    }

    @DeleteMapping("/delete/others/{otherId}")
    void deleteOthers(@PathVariable Long otherId) {
        this.otherRepository.deleteById(otherId);
    }


}

