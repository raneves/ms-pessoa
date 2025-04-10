package br.edu.romulo.ms_pessoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.romulo.ms_pessoa.dto.DadosAtualizacaoPessoaDTO;
import br.edu.romulo.ms_pessoa.dto.DadosCadastroPessoaDTO;
import br.edu.romulo.ms_pessoa.dto.DadosListagemPessoaDTO;
import br.edu.romulo.ms_pessoa.entidade.Pessoa;
import br.edu.romulo.ms_pessoa.repository.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pessoas")
public class PessoaController {	
	
	private @Autowired PessoaRepository pessoaRepository;
	
	//metodo para realizar a exclusao logica
	@Transactional
	@DeleteMapping("exclusaologica/{id}")//parametro dinamico, o id do campo a ser removido
	public void exclusaoLogica(@PathVariable Long id) {
		//carregar o objeto pelo id
        var pessoa = pessoaRepository.getReferenceById(id);
        //setar para inativo
        pessoa.excluir();
	}
	
	
	
	//este metodo remove o registro no banco de dados
	@Transactional
	@DeleteMapping("/{id}")//parametro dinamico, o id do campo a ser removido
	public void excluir(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}

	
	@PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPessoaDTO dados) {
		//carregar o objeto pelo id
		//http://localhost:8080/pessoas?id=1
         var pessoa = pessoaRepository.getReferenceById(dados.id());
         pessoa.atualizarInformacoes(dados);
    }
	
	//se nao informar o sie e o sort no post main, o padrao sera 10 e sort por nome
	@GetMapping("/listar-paginado-ordenado")
	public Page<DadosListagemPessoaDTO> listarPaginadoOrdenado(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
	    //precisamos retornar um DTO
	   		
		//http://localhost:8080/pessoas/listar-paginado-ordenado?sort=nome
		//http://localhost:8080/pessoas/listar-paginado-ordenado?sort=nome,desc		
	
	    //carregar todos os registros que os usuarios esteja ativos
	    return pessoaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPessoaDTO::new);
	 }

	
	
	//Cuidado na hora de importar. Selecione Pageable e Page org.springframework.data.domain e não Pageable java.awt.print.	
	@GetMapping("/listar-paginado")
    public Page<DadosListagemPessoaDTO> listar(Pageable paginacao) {
		//http://localhost:8080/pessoas/listar-paginado-ordenado?size=1
		//http://localhost:8080/pessoas/listar-paginado-ordenado?size=1&page=1
        return pessoaRepository.findAll(paginacao).map(DadosListagemPessoaDTO::new);
    }	
	
	@GetMapping
    public List<DadosListagemPessoaDTO> listar() {		
		 return pessoaRepository.findAll()
			        .stream()
			        .map(DadosListagemPessoaDTO::new)
			        .toList();
    }	
	
	@PostMapping
    public void cadastrar(@RequestBody DadosCadastroPessoaDTO dados) {
           pessoaRepository.save(new Pessoa(dados));
    }
	
	
	
	
	
	 //@PostMapping
     //public void cadastrar(@RequestBody DadosCadastroPessoaDTO dados) {
     //      System.out.println(dados);
     //}
	
//	@PostMapping
//    public void cadastrar(@RequestBody String json) {
//         System.out.println(json);
//	}
	
	
	
}
