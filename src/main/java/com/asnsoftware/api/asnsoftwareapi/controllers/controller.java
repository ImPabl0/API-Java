package com.asnsoftware.api.asnsoftwareapi.controllers;



import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asnsoftware.api.asnsoftwareapi.ClientRepository;
import com.asnsoftware.api.asnsoftwareapi.model.Cliente;
@RestController
@ComponentScan ({"controllers", "reposistories"})
public class controller {
    String[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(12);
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt,10,512);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = skf.generateSecret(pbeKeySpec).getEncoded();
        String[] result = new String[2];
        result[0] = salt.toString();
        result[1] = hash.toString();
        return result;
    }
    
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/register")
    
    public ResponseEntity<String> saveCliente(@Validated @RequestBody Cliente cliente) throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        String[] hashpassword = hashPassword(cliente.getSenha());
        cliente.setSalt(hashpassword[0]);
        cliente.setSenha(hashpassword[1]);
        Cliente cpf = clientRepository.findByCpf(cliente.getCpf());
        Cliente email = clientRepository.findByEmail(cliente.getEmail());
       if(cpf == null && email == null){
        clientRepository.save(cliente);
        return new ResponseEntity<>(null, HttpStatus.OK);
       }else{
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
       }
       
    }
}
