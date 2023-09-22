package br.com.kevin.api.clientapi.service.impl;

import br.com.kevin.api.clientapi.dto.ClienteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.kevin.api.clientapi.entity.Cliente;
import br.com.kevin.api.clientapi.entity.Endereco;
import br.com.kevin.api.clientapi.exceptions.ObjectNotFoundException;
import br.com.kevin.api.clientapi.repository.ClienteRepository;
import br.com.kevin.api.clientapi.service.ClienteService;
import br.com.kevin.api.clientapi.service.EnderecoService;

import java.util.function.Function;

@Service
public class ClienteServiceImpl implements ClienteService {

    private Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);
    private ClienteRepository clienteRepository;
    private EnderecoService enderecoService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    @Override
    public Page<ClienteDto> buscarTodos(Integer page, Integer linesPerPage, String orderBy, String direction) {
        this.logger.info("Getting all Clients");
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        var entities = clienteRepository.findAll(pageRequest);
        Page<ClienteDto> dtos  = entities.map(this::toDto);
        return dtos;
    }

    @Override
    public ClienteDto buscarPorId(Long id) {
        var clientResult = clienteRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Client Not Found: " + ClienteServiceImpl.class.getName()));
        return toDto(clientResult);
    }

    @Override
    public ClienteDto inserir(Cliente cliente) {
        this.logger.info("Trying to insert Client");
        Endereco endereco = enderecoService.buscarEndereco(cliente.getEndereco().getCep());
        cliente.setEndereco(endereco);
        var clientResult = clienteRepository.save(cliente);
        return toDto(clientResult);
    }

    @Override
    public void atualizar(Cliente cliente) {
        this.logger.info("Trying to update Client");
        clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Client not found or does not exists!"));
        this.inserir(cliente);
    }

    @Override
    public void deletar(Long id) {
        this.logger.info("Trying to delete Client");
        clienteRepository.deleteById(id);
    }

    private ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getNome(), cliente.getEndereco());
    }
}
