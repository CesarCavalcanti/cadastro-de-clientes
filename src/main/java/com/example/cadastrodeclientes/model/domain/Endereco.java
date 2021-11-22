package com.example.cadastrodeclientes.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco  {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    @Column (name = "cep")
    private String cep;
    @Column (name = "rua")
    private String rua;
    @Column (name = "complemento")
    private String complemento;
    @Column (name = "bairro")
    private String bairro;
    @Column (name = "cidade")
    private String cidade;
    @Column (name = "uf")
    private String uf;
    @Column (name = "populacao")
    private String populacao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPopulacao() {
        return populacao;
    }

    public void setPopulacao(String populacao) {
        this.populacao = populacao;
    }

    public Endereco() {
    }

    public Endereco( String cep, String rua,String complemento,  String bairro, String cidade, String uf, String populacao) {
        this.complemento = complemento;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.populacao = populacao;
    }
}
