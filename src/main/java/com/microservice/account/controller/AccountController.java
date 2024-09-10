package com.microservice.account.controller;


import com.microservice.account.constants.AccountsConstants;
import com.microservice.account.dto.CustomerDto;
import com.microservice.account.dto.ResponseDto;
import com.microservice.account.service.IaccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private IaccountService iaccountService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iaccountService.createAccount(customerDto);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    @GetMapping("/find")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})"
                                                            ,message = "Mobile number must be 10 digits")
                                                               String mobileNumber) {

        CustomerDto customerDto = iaccountService.getAccount(mobileNumber);


        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/del")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})"
                                                                    ,message = "Mobile number must be 10 digits")
                                                                    String mobileNumber) {
        boolean isDeleted = iaccountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
