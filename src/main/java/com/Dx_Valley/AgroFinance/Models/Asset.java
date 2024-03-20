package com.Dx_Valley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;
    private String assetName;
    private String assetDescription;
    private String assetWeight;
    private Long assetIncrement;
    private Long assetIntervalStart;
    private Long assetIntervalEnd;
    private Long assetStartValue;
    private Long assetEndValue;
    private Long assetStandard;
    private Boolean isValueIncreasing;
}
