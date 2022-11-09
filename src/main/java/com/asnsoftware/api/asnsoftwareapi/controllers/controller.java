package com.asnsoftware.api.asnsoftwareapi.controllers;



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
    
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/register")
    
    public ResponseEntity<String> saveCliente(@Validated @RequestBody Cliente cliente){
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
