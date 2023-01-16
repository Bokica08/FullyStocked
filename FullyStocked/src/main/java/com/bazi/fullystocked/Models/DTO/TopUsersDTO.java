package com.bazi.fullystocked.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopUsersDTO {
    String firstname;
    String lastname;
    String username;
    String email;
    BigInteger topinvoicesum;
    String toparticlename;
    Integer toparticleprice;
    Integer toparticlequantity;
    Integer toparticletotalprice;
}
