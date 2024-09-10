package com.microservice.account.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account Number cant be a null or empty.")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cant be a null or empty.")

    private String accountType;

    @NotEmpty(message = "Branch Address cant not be a null or empty.")

    private String branchAddress;
}
