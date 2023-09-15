package br.com.kevin.api.clientapi.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kevin.api.clientapi.entity.Cliente;
import br.com.kevin.api.clientapi.repository.ClienteRepository;
import br.com.kevin.api.clientapi.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        Sort sort = Sort.by("nome").ascending();
        return clienteRepository.findAll(sort);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Cliente found = clienteRepository.findById(id).get();
        if (found != null) {
            clienteRepository.save(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
