package br.com.kevin.api.clientapi.service;

import br.com.kevin.api.clientapi.entity.Endereco;

public interface EnderecoService {

    void insert(Endereco endereco);

    Endereco buscarEndereco(String cep);

    Endereco buscarCep(String cep);
}