package com.kingcode.picpay_simplificado.service;

import com.kingcode.picpay_simplificado.domain.transaction.Transaction;
import com.kingcode.picpay_simplificado.domain.user.User;
import com.kingcode.picpay_simplificado.dtos.TransactionDTO;
import com.kingcode.picpay_simplificado.repositories.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository repository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    public NotificationService notificationService;
    
    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());
        
        userService.validateTransaction(sender, transaction.value());
        
        boolean isAuthorized = authorizeTransaction(sender, transaction.value());
        if (!isAuthorized){
            throw new Exception("Transaction not authorized!");
        }
        
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());
        
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));
        
        this.repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
        
        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!");
        
        return newTransaction;
    }
    
    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
        
        if (response.getStatusCode() == HttpStatus.OK){
            String message = (String) response.getBody().get("status");
            return "success".equalsIgnoreCase(message);
        }else return false;
    }
    
}
