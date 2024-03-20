package com.Dx_Valley.AgroFinance.Repository;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<AssetWithStatus, Long> {
    AssetWithStatus findByStatusName(String statusName);
    AssetWithStatus findByStatusId(Long statusId);

}


