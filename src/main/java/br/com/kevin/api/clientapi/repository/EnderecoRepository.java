package br.com.kevin.api.clientapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kevin.api.clientapi.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {

}
