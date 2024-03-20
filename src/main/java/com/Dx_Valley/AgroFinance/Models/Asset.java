package com.Dx_Valley.AgroFinance.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;
    private String assetName;
    private String assetDescription;
    private String assetWeight;
    private Long assetIncrement;
    private Long asset_intervalStart;
    private Long asset_intervalEnd;
    private Long asset_startValue;
    private Long asset_endValue;

    public  Asset(String assetName, String assetDescription, String assetWeight, Long assetIncrement,
                  Long asset_intervalStart, Long asset_intervalEnd, Long asset_startValue, Long asset_endValue){
        this.assetName=assetName;
        this.assetDescription =assetDescription;
        this.assetWeight =assetWeight;
        this.assetIncrement=assetIncrement;
        this.asset_intervalStart=asset_intervalStart;
        this.asset_intervalEnd=asset_intervalEnd;
        this.asset_startValue=asset_startValue;
        this.asset_endValue=asset_endValue;
    }
}
