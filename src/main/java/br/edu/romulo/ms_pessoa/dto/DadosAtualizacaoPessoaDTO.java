package br.edu.romulo.ms_pessoa.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoaDTO(@NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEnderecoDTO endereco) {
}
