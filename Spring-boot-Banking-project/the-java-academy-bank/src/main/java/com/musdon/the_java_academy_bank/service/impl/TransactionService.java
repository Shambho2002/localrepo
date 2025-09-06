package com.musdon.the_java_academy_bank.service.impl;

import com.musdon.the_java_academy_bank.dto.TransactionDto;
import com.musdon.the_java_academy_bank.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
}
