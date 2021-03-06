package com.co.linadev.raul_hardware_backend.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private String id;
    private String productId;
    private String billId;
    private Integer amount;

}
