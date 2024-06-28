package com.kingcode.picpay_simplificado.service;

import com.kingcode.picpay_simplificado.domain.user.User;
import com.kingcode.picpay_simplificado.domain.user.UserType;
import com.kingcode.picpay_simplificado.dtos.UserDTO;
import com.kingcode.picpay_simplificado.repositories.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository; 
            
    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchat can't create a transaction!");
        }
        
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance to fisnish the transaction!");
        }
    }
    
    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found!"));
    }
    
    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
    
    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
    
    public void saveUser(User user){
        this.repository.save(user);
    }
    
}
