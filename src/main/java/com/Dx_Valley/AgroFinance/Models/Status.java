package com.Dx_Valley.AgroFinance.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private String statusName;
    private String statusDescription;
    private Long statusWeight;

    @Transient
    private List<String> statusValue;

    public Status(String statusName, String statusDescription, Long statusWeight, List<String> statusValue) {
        this.statusName = statusName;
        this.statusDescription = statusDescription;
        this.statusWeight = statusWeight;
        this.statusValue = statusValue;
        // Ensure that statusValue is consistent with other fields
        this.statusValue =statusValue;
    }

}