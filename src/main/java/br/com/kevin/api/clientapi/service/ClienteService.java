package br.com.kevin.api.clientapi.service;

import br.com.kevin.api.clientapi.dto.ClienteDto;
import org.springframework.data.domain.Page;

import br.com.kevin.api.clientapi.entity.Cliente;

public interface ClienteService {

    Page<ClienteDto> buscarTodos(Integer page, Integer linesPerPage, String orderBy, String direction);

    ClienteDto buscarPorId(Long id);

    ClienteDto inserir(Cliente cliente);

    void atualizar(Cliente cliente);

    void deletar(Long id);
}
