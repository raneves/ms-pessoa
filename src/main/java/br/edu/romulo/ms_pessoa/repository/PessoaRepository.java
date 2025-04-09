package br.edu.romulo.ms_pessoa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.romulo.ms_pessoa.entidade.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	Page<Pessoa> findAllByAtivoTrue(Pageable paginacao);
}
