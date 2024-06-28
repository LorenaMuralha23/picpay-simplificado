package com.kingcode.picpay_simplificado.repositories;

import com.kingcode.picpay_simplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}
