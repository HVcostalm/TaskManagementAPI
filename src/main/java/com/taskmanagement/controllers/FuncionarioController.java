package com.taskmanagement.controllers;

import java.util.List;
import java.time.LocalDate;

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
import com.taskmanagement.entities.Status;
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
	
	private LocalDate data;
	private Funcionario funcionario = new Funcionario();
	private Projeto projeto = new Projeto();
	private Tarefa tarefa = new Tarefa();
	private FuncionarioProjeto funcionario_projeto = new FuncionarioProjeto();
	private Status status;
	
	// GET
		
	@GetMapping(value="/projeto-funcionario/{id_funcionario}")
	public Projeto lerProjeto(@PathVariable Long id_funcionario) { // Somente Junior e Senior
		if( (funcionarioService.encontrarSenior(id_funcionario)) || (funcionarioService.encontrarJunior(id_funcionario)) ) {
			return funcionarioService.exibirProjetoFuncionario(id_funcionario);
		}
		else
			System.out.println("Junior ou Senior inexistente");
			return null;
	}
	
	@GetMapping(value="/tarefas/{id_funcionario}/{id_projeto}")
	public List<Tarefa> lerTarefasProjeto(@PathVariable Long id_funcionario, @PathVariable Long id_projeto) {
		if( (funcionarioService.encontrarSenior(id_funcionario)) || (funcionarioService.encontrarJunior(id_funcionario)) ) {
			this.projeto = projetoRepository.findById(id_projeto).get();
			if(this.projeto!=null && this.projeto.isStatus()==true) {
				return funcionarioService.listarTarefasProjeto(projeto);
			}
			else
				System.out.println("Projeto inexistente ou concluido");
		}
		else
			System.out.println("Junior ou Senior inexistente");
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
	
	@GetMapping(value="/funcionarios-juniores/{id_funcionario}")
	public List<Funcionario> lerFuncionariosJuniores(@PathVariable Long id_funcionario){
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			return funcionarioService.listarJuniores();		
		}
		else {
			System.out.println("Somente o administrador pode realizar essa ação");
			return null;
		}
		
	}
	
	@GetMapping(value="/funcionarios-seniores/{id_funcionario}")
	public List<Funcionario> lerFuncionariosSeniores(@PathVariable Long id_funcionario){
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			return funcionarioService.listarSeniores();		
		}
		else {
			System.out.println("Somente o administrador pode realizar essa ação");
			return null;
		}
		
	}
	
	@GetMapping(value="/administradores/{id_funcionario}")
	public List<Funcionario> lerAdministradores(@PathVariable Long id_funcionario){
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			return funcionarioService.listarAdministradores();
		}
		else {
			System.out.println("Somente o administrador pode realizar essa ação");
			return null;
		}
		
	}
	
	@GetMapping(value="/projetos/{id_funcionario}")
	public List<Projeto> listarProjetos(@PathVariable Long id_funcionario) { // Somente administrador
		if(funcionarioService.encontrarAdministrador(id_funcionario)) {
			return projetoRepository.findAll();
		}
		else {
			System.out.println("Somente o administrador pode realizar essa ação");
			return null;
		}
	}
	
	// POST
	
	@PostMapping(value = "/tarefa/{id_funcionario_senior}/{id_projeto}") // Somente Senior
	public void createTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id_projeto,  @PathVariable Long id_funcionario_senior) {
		if(funcionarioService.encontrarSenior(id_funcionario_senior)) {
			if(funcionarioService.verificarExisteProjeto(id_projeto)) {
				if(funcionarioService.verificarSeniorProjeto(id_funcionario_senior, id_projeto)) {
					System.out.println("Tarefa criada com sucesso");
					tarefaRepository.save(tarefa);
				}
				else
					System.out.println("Este funcionario senior não foi atribuido a este projeto");
			}
			else
				System.out.println("Projeto inexistente");
		}
		else
			System.out.println("Somente o senior pode realizar essa ação");
		
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
				this.funcionario = funcionarioRepository.findById(id_funcionario_senior).get();
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
	@PutMapping(value="/atualizar-senha-email/{id_funcionario}")
	public Funcionario updateFuncionario(@RequestBody Funcionario funcionario, @PathVariable Long id_funcionario) {
		if(funcionarioService.encontrarFuncionarioParaAtualizarInformacao(id_funcionario)) {
			if(funcionarioService.verificarSomenteSenhaEmailAlterados(funcionario, id_funcionario)) {
				System.out.println("Senha ou email atualizado");
				return funcionarioRepository.save(funcionario);
			}
			else
				System.out.println("Outra informacao que não é senha ou email foi atualizada");
		}
		else
			System.out.println("Funcionario inexistente");
		
		return funcionarioRepository.findById(id_funcionario).get();
	}
	
	// Somente senior e um total de 7 funcionarios
	@PutMapping(value="/atribuirProjetoFuncionario/{id_funcionario}/{id_projeto}/{id_funcionario_junior_senior}")
	public void atribuirProjetoFuncionario(@PathVariable Long id_funcionario, @PathVariable Long id_projeto, @PathVariable Long id_funcionario_junior_senior) {
		if(funcionarioService.encontrarSenior(id_funcionario)) {
			if(funcionarioService.verificarExisteProjeto(id_projeto) && this.projeto.isStatus()==true) {
				if(funcionarioService.verificarSeniorProjeto(id_funcionario, id_projeto)) {
					if(funcionarioService.verificarQuantidadeFuncionariosProjeto(id_projeto)) {
						this.projeto = projetoRepository.findById(id_projeto).get();
						
						this.funcionario = funcionarioRepository.findById(id_funcionario).get();
						this.funcionario.setProjeto(this.projeto);
						funcionarioRepository.save(this.funcionario);
						
						this.funcionario_projeto.setData_participacao_inicial(data);
						this.funcionario_projeto.setData_participacao_final(null);
						this.funcionario_projeto.setFuncionario(this.funcionario);
						this.funcionario_projeto.setProjeto(this.projeto);
						
						funcionarioProjetoRepository.save(this.funcionario_projeto);
					}
					else
						System.out.println("Projeto cheio");
				}
				else
					System.out.println("Este senior não foi atribuido para este projeto");
			}
			else
				System.out.println("Projeto inexistente ou concluido");
		}
		else
			System.out.println("Senior inexistente. Apenas o senior pode realizar essa ação");
		
	}
	
	@PutMapping(value="/iniciar-tarefa/{id_funcionario}/{id_tarefa}")
	public void iniciarTarefa(@PathVariable Long id_tarefa, @PathVariable Long id_funcionario) {
		if( (funcionarioService.encontrarSenior(id_funcionario)) || (funcionarioService.encontrarJunior(id_funcionario)) ) {
			if(funcionarioService.encontrarTarefa(id_tarefa)) {
				this.funcionario = funcionarioRepository.findById(id_funcionario).get();
				this.tarefa = tarefaRepository.findById(id_funcionario).get();
				this.projeto = projetoRepository.findById(this.funcionario.getProjeto().getId_projeto()).get();
				if(this.projeto.isStatus()==true) {
					if(this.funcionario.getProjeto()==this.tarefa.getProjeto()) {
						this.tarefa.setStatus(status.EmAndamento);
						tarefaRepository.save(this.tarefa);
					}
					else
						System.out.println("Esta tarefa não faz parte do projeto que o funcionario foi atribuido");
				}
				else
					System.out.println("Projeto já foi concluido");
			}
			else
				System.out.println("Tarefa inexistente");
			
		}
		else
			System.out.println("Junior ou Senior inexistente");
		
	}
	
	@PutMapping(value="/finalizar-tarefa/{id_funcionario}/{id_tarefa}")
	public void finalizarTarefa(@PathVariable Long id_tarefa, @PathVariable Long id_funcionario) {
		if( (funcionarioService.encontrarSenior(id_funcionario)) || (funcionarioService.encontrarJunior(id_funcionario)) ) {
			if(funcionarioService.encontrarTarefa(id_tarefa)) {
				this.funcionario = funcionarioRepository.findById(id_funcionario).get();
				this.tarefa = tarefaRepository.findById(id_funcionario).get();
				this.projeto = projetoRepository.findById(this.funcionario.getProjeto().getId_projeto()).get();
				if(this.projeto.isStatus()==true) {
					if(this.funcionario.getProjeto()==this.tarefa.getProjeto()) {
						this.tarefa.setStatus(status.Concluido);
						tarefaRepository.save(this.tarefa);
					}
					else
						System.out.println("Esta tarefa não faz parte do projeto que o funcionario foi atribuido");
				}
				else
					System.out.println("Projeto já foi concluido");
			}
			else
				System.out.println("Tarefa inexistente");
			
		}
		else
			System.out.println("Junior ou Senior inexistente");
	}
	
	@PutMapping(value="/finalizar-tarefa/{id_funcionario}/{id_tarefa}")
	public void atualizarTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id_tarefa, @PathVariable Long id_funcionario) {
		if( (funcionarioService.encontrarSenior(id_funcionario)) || (funcionarioService.encontrarJunior(id_funcionario)) ) {
			if(funcionarioService.encontrarTarefa(id_tarefa)) {
				this.funcionario = funcionarioRepository.findById(id_funcionario).get();
				this.tarefa = tarefaRepository.findById(id_funcionario).get();
				this.projeto = projetoRepository.findById(this.funcionario.getProjeto().getId_projeto()).get();
				if(this.projeto.isStatus()==true) {
					if(this.funcionario.getProjeto()==this.tarefa.getProjeto()) {
						if(funcionarioService.verificarSomentePrioridadeAlterada(tarefa, id_tarefa)) {
							tarefaRepository.save(tarefa);
						}
						else
							System.out.println("Outra informação que não é a prioridade foi alterada");
						
					}
					else
						System.out.println("Esta tarefa não faz parte do projeto que o funcionario foi atribuido");
				}
				else
					System.out.println("Projeto já foi concluido");
			}
			else
				System.out.println("Tarefa inexistente");
			
		}
		else
			System.out.println("Junior ou Senior inexistente");
	}
	
	// deixar null em senior e junior e false status
	@PutMapping(value = "/finalizar-projeto/{id_funcionario}/{idProjeto}") // Somente senior
	public void finalizarProjeto(@PathVariable Long id_funcionario, @PathVariable Long id_projeto) {
		this.projeto = projetoRepository.findById(id_projeto).get();
		if(funcionarioService.encontrarSenior(id_funcionario)) {
			if( (funcionarioService.verificarExisteProjeto(id_projeto)) && (this.projeto.isStatus()==true)) {
				if(funcionarioService.verificarTodasTarefasConcluidas(this.projeto)) {
					this.projeto.setStatus(false);
					this.projeto.setData_conclusao(data);
					projetoRepository.save(this.projeto);
					funcionarioService.finalizarProjetoFuncionarios(this.projeto);
				}
				else
					System.out.println("As tarefas não estão concluidas");
			}
			else
				System.out.println("Projeto inexistente ou concluido");
		}
		else
			System.out.println("Somente o senior pode realizar essa ação");
	}
	
	// DELETE

	@DeleteMapping(value = "/apagar-tarefa/{id_funcionario}/{idTarefa}") // Somente senior
	public void deleteTarefa(@PathVariable Long id_tarefa, @PathVariable Long id_funcionario) {
		if(funcionarioService.encontrarSenior(id_funcionario)) {
			if(funcionarioService.encontrarTarefa(id_tarefa)) {
				this.funcionario = funcionarioRepository.findById(id_funcionario).get();
				this.tarefa = tarefaRepository.findById(id_funcionario).get();
				this.projeto = projetoRepository.findById(this.funcionario.getProjeto().getId_projeto()).get();
				if(this.projeto.isStatus()==true) {
					if (this.funcionario.getProjeto() == this.tarefa.getProjeto()) {
						System.out.println("Tarefa apagada com sucesso");
						tarefaRepository.deleteById(id_tarefa);
					} else
						System.out.println("Esta tarefa não faz parte do projeto que funcionario senior foi atribuido");
				}
				else
					System.out.println("Projeto já foi concluido");
			}
			else
				System.out.println("Tarefa inexistente");
			
		}
		else
			System.out.println("Somente o senior pode realizar essa ação");
		
	}
	
	// deixar null em senior e junior e false status
	@DeleteMapping(value = "/deletar-projeto/{id_funcionario}/{idProjeto}") // Somente administrador
	public void deletarProjeto(@PathVariable Long id_projeto, @PathVariable Long id_funcionario) {
		this.projeto = projetoRepository.findById(id_projeto).get();
		if (funcionarioService.encontrarAdministrador(id_funcionario)) {
			if((funcionarioService.verificarExisteProjeto(id_projeto)) && (this.projeto.isStatus() == true)) {
				funcionarioService.finalizarProjetoFuncionarios(this.projeto);
				projetoRepository.deleteById(id_projeto);
			} else
				System.out.println("Projeto inexistente ou concluido");
		} else
			System.out.println("Somente o administrador pode realizar essa ação");
	}
}
