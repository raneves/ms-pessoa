package br.edu.romulo.ms_pessoa.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPessoaDTO(
		@NotBlank String nome, 
		@NotBlank @Email String email,
		@NotBlank String telefone,
		@NotNull @Valid DadosEnderecoDTO endereco) {
}
