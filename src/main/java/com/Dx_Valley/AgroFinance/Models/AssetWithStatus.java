package com.Dx_Valley.AgroFinance.Models;


import jakarta.persistence.*;
import lombok.*;

import com.Dx_Valley.AgroFinance.Enums.Status;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AssetWithStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private String statusName;
    private String statusDescription;
    private Long statusWeight;
    private Status statusValue;

}