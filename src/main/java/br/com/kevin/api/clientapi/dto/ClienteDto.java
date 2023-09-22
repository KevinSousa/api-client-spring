package br.com.kevin.api.clientapi.dto;

import br.com.kevin.api.clientapi.entity.Endereco;
import jakarta.persistence.ManyToOne;

public class ClienteDto {
    private String nome;
    private Endereco endereco;

    public ClienteDto(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
