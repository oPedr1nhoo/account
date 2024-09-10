package com.microservice.account.repository;

import com.microservice.account.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, Long> {
}
