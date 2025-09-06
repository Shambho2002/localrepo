package com.musdon.the_java_academy_bank.repository;

import com.musdon.the_java_academy_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
