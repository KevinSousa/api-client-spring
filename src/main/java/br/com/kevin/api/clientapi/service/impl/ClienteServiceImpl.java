package br.com.kevin.api.clientapi.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kevin.api.clientapi.entity.Cliente;
import br.com.kevin.api.clientapi.entity.Endereco;
import br.com.kevin.api.clientapi.exceptions.ObjectNotFoundException;
import br.com.kevin.api.clientapi.repository.ClienteRepository;
import br.com.kevin.api.clientapi.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private EnderecoService enderecoService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        Sort sort = Sort.by("nome").ascending();
        return clienteRepository.findAll(sort);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Client Not Found: " + ClienteServiceImpl.class.getName()));
    }

    @Override
    public void inserir(Cliente cliente) {
        Endereco endereco = enderecoService.buscarEndereco(cliente.getEndereco().getCep());
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Cliente cliente) {
        clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Client not found or does not exists!"));
        this.inserir(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
