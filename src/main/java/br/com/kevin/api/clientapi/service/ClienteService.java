package br.com.kevin.api.clientapi.service;

import org.springframework.data.domain.Page;

import br.com.kevin.api.clientapi.entity.Cliente;

public interface ClienteService {

    Page<Cliente> buscarTodos(Integer page, Integer linesPerPage, String orderBy, String direction);

    Cliente buscarPorId(Long id);

    Cliente inserir(Cliente cliente);

    void atualizar(Cliente cliente);

    void deletar(Long id);
}
