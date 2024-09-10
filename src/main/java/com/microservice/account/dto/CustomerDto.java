package com.microservice.account.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema para carregar dados do customer e da conta"
)
public class CustomerDto {
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the cliente name should be between 5 and 30")
    @Schema(
            description = "Name do customer", example = "Eazy Bytes"
    )
    private String name;
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(
            description = "Endereço de email", example = "qualquer@gmail.com"
    )
    private String email;


  // esse aqui complica mais ai e foda! @Pattern(regexp = "(^$|\\(?[1-9]{2}\\)? ?9?[0-9]{4}-?[0-9]{4})", message = "Número de telefone deve estar no formato válido com DDD e pode incluir o nono dígito")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "número do cliente", example = "9345432123"
    )
    private String mobileNumber;

    @Schema(
            description = "Detalhes da conta do cliente"
    )
    private AccountsDto accountsDto;

}
