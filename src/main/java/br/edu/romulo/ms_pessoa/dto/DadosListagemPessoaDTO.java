package br.edu.romulo.ms_pessoa.dto;

import br.edu.romulo.ms_pessoa.entidade.Pessoa;

public record DadosListagemPessoaDTO(Long id, String nome, String email, String telefone, Boolean ativo) {
	public DadosListagemPessoaDTO(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getAtivo());
    }
}
