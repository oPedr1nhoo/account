package com.microservice.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema para armazenar informações da conta"
)
public class AccountsDto {


    @NotEmpty(message = "Account Number cant be a null or empty.")
    @Schema(
            description = "Número da conta Eazy Bank", example = "3454433243"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account Type cant be a null or empty.")
    @Schema(
            description = "Tipo de conta da conta Eazy Bank", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch Address cant not be a null or empty.")
@Schema
        (
                description = "Endereço do Eazy Bank"
        )
    private String branchAddress;
}
