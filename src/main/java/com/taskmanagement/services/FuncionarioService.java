package com.taskmanagement.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.entities.Funcionario;
import com.taskmanagement.entities.FuncionarioProjeto;
import com.taskmanagement.entities.NivelPermissao;
import com.taskmanagement.entities.Projeto;
import com.taskmanagement.entities.Status;
import com.taskmanagement.entities.Tarefa;
import com.taskmanagement.repositories.FuncionarioProjetoRepository;
import com.taskmanagement.repositories.FuncionarioRepository;
import com.taskmanagement.repositories.ProjetoRepository;
import com.taskmanagement.repositories.TarefaRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionario_repository;
		
	@Autowired
	private TarefaRepository tarefa_repository;
	
	@Autowired
	private ProjetoRepository projeto_repository;
	
	@Autowired
	private FuncionarioProjetoRepository funcionario_projeto_repository;
	
	private Funcionario funcionario;
	private Projeto projeto;
	private Tarefa tarefa;
	private NivelPermissao nivel_permissao;
	private Status status;
	private LocalDate data = LocalDate.now();
	
	public Funcionario encontrarFuncionario(Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		return this.funcionario;
	}
	
	public List<Funcionario> listarFuncionariosAtivos(){
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosAtivos = new ArrayList<>();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.isStatus()==true) {
				funcionariosAtivos.add(funcionario);
			}
		}
		
		return funcionariosAtivos;
	}
	
	public boolean criarProjeto(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		boolean criarProjetoSucesso = true, existeFuncionarioSenior = false;
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario && funcionario.getNivel_permissao()==nivel_permissao.Senior) {
				this.funcionario = funcionario;
				existeFuncionarioSenior = true;
				break;
			}
		}
		
		if(existeFuncionarioSenior) {
			if(this.funcionario.getProjeto()==null) {
				return criarProjetoSucesso;
			}
			else {
				System.out.println("Funcionario Senior está em outro projeto");
				return criarProjetoSucesso = false;
			}
		}
		else {
			System.out.println("Funcionario Senior inexistente");
			return criarProjetoSucesso = false;
		}
	}
	
	public boolean encontrarAdministrador(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		boolean realizarAcao = true, existeAdministrador = false;
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario && funcionario.getNivel_permissao()==nivel_permissao.Administrador && funcionario.isStatus()==true) {
				this.funcionario = funcionario;
				existeAdministrador = true;
				break;
			}
		}
		
		if(existeAdministrador) {
			return realizarAcao;
		}
		else {
			System.out.println("Administrador inexistente");
			return realizarAcao = false;
		}
	}
	
	public List<Funcionario> listarAdministradores(){
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosAdministradores = new ArrayList<>();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getNivel_permissao()==nivel_permissao.Administrador) {
				funcionariosAdministradores.add(funcionario);
			}
		}
		
		return funcionariosAdministradores;
	}
	
	public boolean encontrarSenior(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		boolean realizarAcao = true, existeSenior = false;
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario && funcionario.getNivel_permissao()==nivel_permissao.Senior && funcionario.isStatus()==true) {
				this.funcionario = funcionario;
				existeSenior = true;
				break;
			}
		}
		
		if(existeSenior) {
			return realizarAcao;
		}
		else {
			System.out.println("Senior inexistente");
			return realizarAcao = false;
		}
	}
	
	public List<Funcionario> listarSeniores(){
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosSeniores = new ArrayList<>();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getNivel_permissao()==nivel_permissao.Senior) {
				funcionariosSeniores.add(funcionario);
			}
		}
		
		return funcionariosSeniores;
	}
	
	public boolean encontrarJunior(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		boolean realizarAcao = true, existeJunior = false;
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getId_funcionario() == id_funcionario && funcionario.getNivel_permissao()==nivel_permissao.Junior && funcionario.isStatus()==true) {
				this.funcionario = funcionario;
				existeJunior = true;
				break;
			}
		}
		
		if(existeJunior) {
			return realizarAcao;
		}
		else {
			System.out.println("Junior inexistente");
			return realizarAcao = false;
		}
	}
	
	public List<Funcionario> listarJuniores(){
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosJuniores = new ArrayList<>();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getNivel_permissao()==nivel_permissao.Junior) {
				funcionariosJuniores.add(funcionario);
			}
		}
		
		return funcionariosJuniores;
	}
	
	public boolean verificarExisteProjeto(Long id_projeto) {
		List<Projeto> projetos = new ArrayList<>();
		projetos = projeto_repository.findAll();
		
		for(Projeto projeto: projetos) {
			if(projeto.getId_projeto()==id_projeto) {
				return true;
			}
		}
			return false;
	}
	
	public boolean verificarSeniorProjeto(Long id_funcionario, Long id_projeto) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		this.projeto = projeto_repository.findById(id_projeto).get();
		
		if(this.funcionario.getProjeto()==this.projeto)
			return true;
		else
			return false;
	}
	
	public boolean verificarFuncionarioSemProjeto(Long id_funcionario, Long id_projeto) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		this.projeto = projeto_repository.findById(id_projeto).get();
		
		if(this.funcionario.getProjeto()!=this.projeto || this.funcionario.getProjeto()==null ) {
			return true;
		}
		else {
			return false;
		}
			
	}
	
	public boolean verificarFuncionarioProjetoDemicao(Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		List<Projeto> projetos = new ArrayList<>();
		projetos = projeto_repository.findAll();
		this.projeto=null;
		
		for(Projeto projeto: projetos) {
			if(this.funcionario.getProjeto()==projeto) {
				this.projeto = projeto;
			}
		}
		
		if(this.projeto==null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verificarQuantidadeFuncionariosProjeto(Long id_projeto) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<Funcionario> funcionariosProjeto = new ArrayList<>();
		this.projeto = projeto_repository.findById(id_projeto).get();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getProjeto()==this.projeto)
				funcionariosProjeto.add(funcionario);
		}
		
		if(funcionariosProjeto.size()<7)
			return true;
		
		return false;
	}
	
	public boolean encontrarTarefa(Long id_tarefa) {
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefa_repository.findAll();
		boolean tarefaEncontrada = false;
		
		for(Tarefa tarefa: tarefas) {
			if(tarefa.getId_tarefa()==id_tarefa) {
				tarefaEncontrada = true;
				break;
			}
		}
		
		if(tarefaEncontrada)
			return true;
		else
			return false;
	}
	
	public boolean encontrarFuncionarioParaAtualizarInformacao(Long id_funcionario) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		
		for(Funcionario funcionario: funcionarios) {
			if( (funcionario.getId_funcionario() == id_funcionario) && (funcionario.isStatus()==true) ) {
				return true;
			}
		}
		return false;
	}
	
	
	public List<Tarefa> pegarTodasTarefasProjeto(Projeto projeto, List<Tarefa> tarefasProjeto){
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefa_repository.findAll();
		
		for(Tarefa tarefa: tarefas) {
			if(tarefa.getProjeto()==projeto) {
				tarefasProjeto.add(tarefa);
			}
		}
		
		return tarefasProjeto;
	}
	
	public boolean verificarTodasTarefasConcluidas(Projeto projeto) {
		List<Tarefa> tarefasProjeto = new ArrayList<>();
		int contadorTarefasConcluidas = 0;
		
		tarefasProjeto = this.pegarTodasTarefasProjeto(projeto, tarefasProjeto);
		
		for(Tarefa tarefa: tarefasProjeto) {
			if(tarefa.getStatus()==status.Concluido) {
				contadorTarefasConcluidas++;
			}
		}
		
		if(tarefasProjeto.size()==contadorTarefasConcluidas) {
			return true;
		}
		else
			return false;
	}
	
	public void finalizarProjetoFuncionarios(Projeto projeto) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios = funcionario_repository.findAll();
		List<FuncionarioProjeto> funcionariosProjetos = new ArrayList<>();
		funcionariosProjetos = funcionario_projeto_repository.findAll();
		
		for(Funcionario funcionario: funcionarios) {
			if(funcionario.getProjeto()==projeto) {
				funcionario.setProjeto(null);
				funcionario_repository.save(funcionario);
			}
		}
		
		for(FuncionarioProjeto funcionarioProjeto: funcionariosProjetos) {
			if(funcionarioProjeto.getProjeto()==projeto) {
				funcionarioProjeto.setData_participacao_final(data);
				funcionario_projeto_repository.save(funcionarioProjeto);
			}
		}
		
	}
	
	public boolean verificarSomenteMudancaSenhaEmailAlterados(Funcionario funcionario, Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		
		if( (this.funcionario.getProjeto()==funcionario.getProjeto()) && (this.funcionario.getNivel_permissao()==funcionario.getNivel_permissao()) && (this.funcionario.getLogin_funcionario().equalsIgnoreCase(funcionario.getLogin_funcionario())) && (this.funcionario.getId_funcionario()==funcionario.getId_funcionario())  ) {
			return true;
		}
		
		return false;
	}
	
	public boolean verificarSomenteMudancaNomeDescricaoPrioridadeAlterada(Tarefa tarefa, Long id_tarefa) {
		this.tarefa = tarefa_repository.findById(id_tarefa).get();
		
		if(this.tarefa.getProjeto().getId_projeto()==tarefa.getProjeto().getId_projeto() && this.tarefa.getStatus()==tarefa.getStatus() && this.tarefa.getId_tarefa()==tarefa.getId_tarefa()) {
			return true;
		}
		
		return false;
	}
	
	public boolean verificarSomenteMudancaNomeDescricao(Projeto projeto, Long id_projeto){
		this.projeto = projeto_repository.findById(id_projeto).get();
		
		if(this.projeto.getId_projeto()==projeto.getId_projeto() && this.projeto.getData_prevista_entrega()==projeto.getData_prevista_entrega() && this.projeto.getData_conclusao()==projeto.getData_conclusao() && this.projeto.isStatus()==projeto.isStatus()) {
			return true;
		}
		return false;
	}
	
	public List<Tarefa> listarTarefasProjeto(Projeto projeto){
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefa_repository.findAll();
		List<Tarefa> tarefasProjeto = new ArrayList<>();
		
		for(Tarefa tarefa: tarefas) {
			if(tarefa.getProjeto()==projeto) {
				tarefasProjeto.add(tarefa);
			}
		}
		
		if(tarefasProjeto.isEmpty()) {
			System.out.println("Nenhuma tarefa foi criada");
		}
		
		return tarefasProjeto;
	}
	
	
	public Projeto exibirProjetoFuncionario(Long id_funcionario) {
		this.funcionario = funcionario_repository.findById(id_funcionario).get();
		List<Projeto> projetos = new ArrayList<>();
		projetos = projeto_repository.findAll();
		
		for(Projeto projeto: projetos) {
			if(this.funcionario.getProjeto()==projeto) {
				this.projeto = projeto;
				return this.projeto;
			}
		}
		System.out.println("Este funcionario não tem um projeto atribuido");
		return null;
	}
	
	public List<Projeto> mostrarProjetosAtivos(){
		List<Projeto> projetos = projeto_repository.findAll(); 
		List<Projeto> projetosAtivos = new ArrayList<>();
		for(Projeto projeto: projetos) {
			if(projeto.isStatus()==true) {
				projetosAtivos.add(projeto);
			}
		}
		
		if(projetosAtivos.isEmpty()) {
			System.out.println("Nenhum projeto ativo");
			return null;
		}
		else
			return projetosAtivos;		
	}	
}
