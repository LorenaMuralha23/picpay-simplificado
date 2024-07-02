package com.kingcode.picpay_simplificado.controllers;

import com.kingcode.picpay_simplificado.domain.transaction.Transaction;
import com.kingcode.picpay_simplificado.dtos.TransactionDTO;
import com.kingcode.picpay_simplificado.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService service;
    
    @PostMapping
    public ResponseEntity<Transaction> createNewTransaction(@RequestBody TransactionDTO data) throws Exception{
        Transaction newTransaction = this.service.createTransaction(data);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
    
}
