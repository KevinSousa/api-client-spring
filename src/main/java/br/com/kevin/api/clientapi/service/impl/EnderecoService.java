package br.com.kevin.api.clientapi.service.impl;

import org.springframework.stereotype.Service;

import br.com.kevin.api.clientapi.repository.EnderecoRepository;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
}
