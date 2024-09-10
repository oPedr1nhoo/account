package com.microservice.account.service.impl;

import com.microservice.account.constants.AccountsConstants;
import com.microservice.account.dto.AccountsDto;
import com.microservice.account.dto.CustomerDto;
import com.microservice.account.entity.Accounts;
import com.microservice.account.entity.Customer;
import com.microservice.account.exception.CustomerAlredyExistentExceptin;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.mapper.AccountsMapper;
import com.microservice.account.mapper.CustomerMapper;
import com.microservice.account.repository.AccountRepository;
import com.microservice.account.repository.CustomerRepository;
import com.microservice.account.service.IaccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IaccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent())
        {
            throw new CustomerAlredyExistentExceptin("Customer alredy registered with given mobileNumber" +customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }


    private Accounts createNewAccount(Customer customer)
    {
        Accounts newAccount = new Accounts();

        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);

        newAccount.setAccountType(AccountsConstants.SAVINGS);

        newAccount.setBranchAdress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto getAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new  ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
        );

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new  ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {


        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );


            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );


            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);


                         isUpdated = true;
        }
                        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        return false;
    }
}
