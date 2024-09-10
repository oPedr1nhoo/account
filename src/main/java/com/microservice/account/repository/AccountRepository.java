package com.microservice.account.repository;

import com.microservice.account.entity.Accounts;
import com.microservice.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, Long> {


    Optional<Accounts> findByCustomerId(Long customerId);
}
