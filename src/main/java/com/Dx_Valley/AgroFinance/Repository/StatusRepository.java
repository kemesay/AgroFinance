package com.Dx_Valley.AgroFinance.Repository;
import com.Dx_Valley.AgroFinance.Models.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(String statusName);
    Status findByStatusId(Long statusId);

}


