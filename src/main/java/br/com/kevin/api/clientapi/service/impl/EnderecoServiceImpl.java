package br.com.kevin.api.clientapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.kevin.api.clientapi.entity.Endereco;
import br.com.kevin.api.clientapi.exceptions.ObjectNotFoundException;
import br.com.kevin.api.clientapi.repository.EnderecoRepository;
import br.com.kevin.api.clientapi.service.EnderecoService;
import br.com.kevin.api.clientapi.service.ViaCepService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private Logger logger = LoggerFactory.getLogger(EnderecoServiceImpl.class);
    private EnderecoRepository enderecoRepository;
    private ViaCepService viaCepService;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    public void insert(Endereco endereco) {
        this.logger.info("Trying to insert Address");
        enderecoRepository.save(endereco);
    }

    public Endereco buscarEndereco(String cep) {
        this.logger.info("Trying to insert Address");
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = this.buscarCep(cep);
            this.insert(novoEndereco);
            return novoEndereco;
        });
        return endereco;
    }

    public Endereco buscarCep(String cep) {
        this.logger.info("Getting Address from external API");
        return viaCepService.consultarCep(cep)
                .orElseThrow(() -> new ObjectNotFoundException("CEP not found or does not exists"));
    }
}
