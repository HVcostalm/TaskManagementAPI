package com.taskmanagement.controllers;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entities.Funcionario;
import com.taskmanagement.entities.FuncionarioProjeto;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioProjetoRepository;
import com.taskmanagement.repositories.FuncionarioRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;
import com.taskmanagement.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private FuncionarioProjetoRepository funcionarioProjetoRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	private Date data = new Date();
	private Funcionario funcionario;
	private Projeto projeto;
	private Tarefa tarefa;
	private FuncionarioProjeto funcionario_projeto;
	
	// GET
		
	@GetMapping(value="/projeto/{id_funcionario}/{id_projeto}")
	public Projeto lerProjeto(@PathVariable Long id_funcionario, @PathVariable Long id_projeto) { // Somente Junior e Senior
		
		return projetoRepository.findById(id_projeto).get();
	}
	
	@GetMapping(value="/tarefas/{id_projeto}")
	public List<Tarefa> lerTarefasProjeto(@PathVariable Long id_projeto) {
		
		return tarefaRepository.findAll();
	}
	
	@GetMapping(value="/tarefa/{idTarefa}/{id_projeto}")
	public Tarefa lerTarefaProjeto(@PathVariable Long id_projeto, @PathVariable Long id_tarefa) {
		
		return tarefaRepository.findById(id_tarefa).get();
	}
	
	@GetMapping(value="/tarefas/{id_funcionario}") // Somente Administrador
	public List<Tarefa> lerTodasTarefas() {
		
		return tarefaRepository.findAll();
	}
	
	// Somente Administrador
	@GetMapping(value="/todos-funcionarios/{id_funcionario}")
	public List<Funcionario> readTodosFuncionarios(@PathVariable Long id_funcionario){
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			return funcionarioRepository.findAll();
		}
		else {
			System.out.println("Somente o administrador pode realizar essa ação");
			return null;
		}
		
	}
	
	@GetMapping(value="/funcionarios-juniores")
	public List<Funcionario> lerFuncionariosJuniores(){
		return funcionarioRepository.findAll();
	}
	
	@GetMapping(value="/funcionarios-seniores")
	public List<Funcionario> lerFuncionariosSeniores(){
		return funcionarioRepository.findAll();
	}
	
	@GetMapping(value="/administradores")
	public List<Funcionario> lerAdministradores(){
		return funcionarioRepository.findAll();
	}
	
	@GetMapping(value="/projetos/{id_funcionario}")
	public List<Projeto> listarProjetos(@PathVariable Long id_funcionario) { // Somente administrador
		return projetoRepository.findAll();
	}
	
	// POST
	
	@PostMapping(value = "/tarefa/{id_projeto}")
	public Tarefa createTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id_projeto) {
		return tarefaRepository.save(tarefa);
	}
	
	@PostMapping(value="/cadastrar-funcionario/{id_funcionario}")
	public void cadastrarFuncionario(@RequestBody Funcionario funcionario, @PathVariable Long id_funcionario) { // Somente administrador
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			System.out.println("Funcionario cadastrado com sucesso");
			funcionarioRepository.save(funcionario);
		}
		else
			System.out.println("Somente o administrador pode realizar essa ação");
		
	}
	
	// Somente vai criar projeto se tiver um senior e ele não estiver em um projeto
	@PostMapping(value="/criar-projeto/{id_funcionario}/{id_funcionario_senior}")// administrador / senior
	public void criarProjeto(@RequestBody Projeto projeto, @PathVariable Long id_funcionario, @PathVariable Long id_funcionario_senior) { 
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			if (funcionarioService.criarProjeto(id_funcionario_senior)) {
				System.out.println("Projeto criado com sucesso");
				projetoRepository.save(projeto);
				this.funcionario = funcionarioService.pegarFuncionario(id_funcionario);
				this.funcionario.setProjeto(projeto);
				funcionarioRepository.save(this.funcionario);
				this.funcionario_projeto.setFuncionario(this.funcionario);
				this.funcionario_projeto.setProjeto(projeto);
				this.funcionario_projeto.setData_participacao_inicial(data);
				funcionarioProjetoRepository.save(this.funcionario_projeto);
			} else {
				System.out.println("Não pode criar projeto sem ter um funcionario senior");
			}
		}
		else
			System.out.println("Somente o administrador pode realizar essa ação");
		
	}
	
	
	// PUT
	
	// senha e email
	@PutMapping(value="/{id_funcionario}")
	public Funcionario updateFuncionarioJunior(@RequestBody Funcionario funcionario, @PathVariable Long id_funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	@PutMapping(value="/tarefa/{id_tarefa}")
	public Tarefa updateTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id_tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	// deixar null em senior e junior e false status
	@PutMapping(value = "/finalizar-projeto/{idProjeto}")
	public void finalizarProjeto(@PathVariable Long id_projeto) {
		
	}
	
	// deixar null em senior e junior e false status
	@PutMapping(value = "/deletar-projeto/{idProjeto}") // deixar false o status
	public void deletarProjeto(@PathVariable Long id_projeto) {
		
	}
	
	// DELETE

	@DeleteMapping(value = "/tarefa/{idTarefa}")
	public void deleteTarefa(@PathVariable Long id_tarefa) {
		tarefaRepository.deleteById(id_tarefa);
	}
}
