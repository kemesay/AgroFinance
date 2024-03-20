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
public class Others {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otherId;
    private String otherName;
    private String otherDescription;
    private String OtherWeight;
    private Long otherIncrement;
    private Long other_intervalStart;
    private Long other_intervalEnd;
    private Long other_startValue;
    private Long other_endValue;

    public  Others(String otherName, String otherDescription, String OtherWeight, Long otherIncrement, Long other_intervalStart, Long other_intervalEnd,
                   Long other_startValue, Long other_endValue){
        this.otherName=otherName;
        this.otherDescription=otherDescription;
        this.OtherWeight= OtherWeight;
        this.otherIncrement=otherIncrement;
        this.other_intervalStart=other_intervalStart;
        this.other_intervalEnd =other_intervalEnd;
        this.other_startValue= other_startValue;
        this.other_endValue=other_endValue;


    }

}