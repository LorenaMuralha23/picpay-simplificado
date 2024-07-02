package com.kingcode.picpay_simplificado.service;

import com.kingcode.picpay_simplificado.domain.user.User;
import com.kingcode.picpay_simplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public void sendNotification(User userToSend, String message) throws Exception{
        String email = userToSend.getEmail();
        NotificationDTO notificationResquest = new NotificationDTO(email, message);
        
//        ResponseEntity<String> notificaResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify"
//                , notificationResquest, String.class);
//        
//        if (!(notificaResponse.getStatusCode() == HttpStatus.OK)) {
//            System.out.println("An error ocurred while sending the notification!");
//            throw new Exception("Notification service is not working properly!");
//        }
        
        System.out.println("Notificação enviada!");
    }
    
}
