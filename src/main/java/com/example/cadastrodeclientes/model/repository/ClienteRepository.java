package com.example.cadastrodeclientes.model.repository;

import com.example.cadastrodeclientes.model.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    @Query(value = "from Cliente c where c.email=:email")
    Cliente findbyEmail(String email);
}
