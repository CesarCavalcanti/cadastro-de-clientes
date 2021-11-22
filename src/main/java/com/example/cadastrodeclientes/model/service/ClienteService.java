package com.example.cadastrodeclientes.model.service;

import com.example.cadastrodeclientes.model.domain.Cliente;
import com.example.cadastrodeclientes.model.domain.Endereco;
import com.example.cadastrodeclientes.model.repository.ClienteRepository;
import com.example.cadastrodeclientes.model.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public void incluirCliente (Cliente cliente,Endereco endereco){
        enderecoRepository.save(endereco);
        clienteRepository.save(cliente);
    }
    public List<Cliente> listarClientes (){
        return (List<Cliente>) clienteRepository.findAll();
    }


    public Cliente editarDados (String email){
        return clienteRepository.findbyEmail(email);
    }
}
