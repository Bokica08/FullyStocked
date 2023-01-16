package com.bazi.fullystocked.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationAnalysisDTO {
    String locationname;
    BigInteger profit;
}
