package com.Dx_Valley.AgroFinance.Repository;
import com.Dx_Valley.AgroFinance.Models.Others;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OtherRepository extends  JpaRepository<Others, Long> {

    Others findByOtherName(String otherName);
    Others findByOtherId(Long otherId);


}


