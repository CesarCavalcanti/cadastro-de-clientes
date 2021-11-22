package com.example.cadastrodeclientes.controller;

import com.example.cadastrodeclientes.model.domain.Cliente;
import com.example.cadastrodeclientes.model.domain.Endereco;
import com.example.cadastrodeclientes.model.service.AmazonS3service;
import com.example.cadastrodeclientes.model.service.ClienteService;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    private AmazonS3service amazonS3service;

    @GetMapping(value = "/")
    public String telaIndex(){
        return "index.html";
    }

    @PostMapping(value = "/clientes")
    public String telaLista(Model model){
        List<String> fotos = amazonS3service.listarFotos();
        model.addAttribute("lista",clienteService.listarClientes());
        model.addAttribute("foto",fotos);
        return "lista.html";
    }

    @GetMapping("/cliente")
    public String telaCadastro (){
        return "cadastro.html";
    }

    @PostMapping(value = "/cliente/incluir")
    public String incluir (Cliente cliente, Endereco endereco, @RequestPart(value = "file")MultipartFile file, Model model){
        clienteService.incluirCliente(cliente,endereco);
        amazonS3service.salvarFotos(file);
        model.addAttribute("nome",cliente.getNome());
        return telaLista(model);
    }
    @GetMapping("/cliente/editar/{email}")
    public String telaAlterar (Model model, @PathVariable String email){
        model.addAttribute("cliente",clienteService.editarDados(email));
        return "alterar.html";
    }

}
