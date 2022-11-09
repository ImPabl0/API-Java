package com.asnsoftware.api.asnsoftwareapi;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.asnsoftware.api.asnsoftwareapi.model.Cliente;


@Repository
@Service
public interface ClientRepository extends CrudRepository<Cliente, Long> {
    Cliente findByCpf(@Param("cpf") String cpf);
    Cliente findByEmail(@Param("email") String email);
}
