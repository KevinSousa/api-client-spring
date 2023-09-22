package br.com.kevin.api.clientapi.controller;

import java.net.URI;

import br.com.kevin.api.clientapi.dto.ClienteDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.kevin.api.clientapi.entity.Cliente;
import br.com.kevin.api.clientapi.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Page<ClienteDto>> buscarTodos(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<ClienteDto> list = clienteService.buscarTodos(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> inserir(@RequestBody Cliente cliente) {
        var userCreated = clienteService.inserir(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        clienteService.atualizar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
