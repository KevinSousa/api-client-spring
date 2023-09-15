package br.com.kevin.api.clientapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kevin.api.clientapi.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
