package com.example.cadastrodeclientes.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email",unique = true)
    @Id
    private String email;
    @ManyToOne
    @JoinColumn (name = "id_endereco")
    private Endereco endereco;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "nomeFoto")
    private String nomeFoto;

    public Cliente() {
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }
}
