package br.edu.romulo.ms_pessoa.entidade;


import br.edu.romulo.ms_pessoa.dto.DadosAtualizacaoPessoaDTO;
import br.edu.romulo.ms_pessoa.dto.DadosCadastroPessoaDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pessoa")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;    
    private String telefone;
    @Embedded
    private Endereco endereco;    
    private Boolean ativo;
    
    public Pessoa(DadosCadastroPessoaDTO dados) {
    	this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();        
        this.endereco = new Endereco(dados.endereco());
    }
    
    public void excluir() {
        this.ativo = false;
    }
    
    public void atualizarInformacoes(DadosAtualizacaoPessoaDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.email() != null) {
        	this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
