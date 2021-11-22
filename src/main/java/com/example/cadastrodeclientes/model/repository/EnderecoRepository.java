package com.example.cadastrodeclientes.model.repository;

import com.example.cadastrodeclientes.model.domain.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco,Integer> {

}
