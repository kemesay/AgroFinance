package com.Dx_Valley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FarmerAgeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double assetIntervalStart;
    private Double assetIntervalEnd;
    private Double assetStartValue;
    private Double assetEndValue;
    private Double assetStandard;
    private Boolean isValueIncreasing;
}
