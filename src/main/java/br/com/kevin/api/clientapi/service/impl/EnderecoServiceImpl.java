package br.com.kevin.api.clientapi.service.impl;

import org.springframework.stereotype.Service;

import br.com.kevin.api.clientapi.entity.Endereco;
import br.com.kevin.api.clientapi.exceptions.ObjectNotFoundException;
import br.com.kevin.api.clientapi.repository.EnderecoRepository;
import br.com.kevin.api.clientapi.service.EnderecoService;
import br.com.kevin.api.clientapi.service.ViaCepService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private EnderecoRepository enderecoRepository;
    private ViaCepService viaCepService;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    public void insert(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public Endereco buscarEndereco(String cep) {
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = this.buscarCep(cep);
            this.insert(novoEndereco);
            return novoEndereco;
        });
        return endereco;
    }

    public Endereco buscarCep(String cep) {
        return viaCepService.consultarCep(cep)
                .orElseThrow(() -> new ObjectNotFoundException("CEP not found or does not exists"));
    }
}
