package br.ifce.agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import br.ifce.mind.Agent;
import br.ifce.mind.Ceed;
import br.ifce.mind.action.AbstractAction;

public class DAOPessoasAgent implements DAOPessoas {
	
	Agent agent;
	public boolean editando = false;
	
	public DAOPessoasAgent() {
		/*Ceed.getAgent("DAOPessoas", new AbstractAction() {
			public void act(Object target, Object callback) {
				agent = (Agent) target;
			}
		});*/
		Ceed.getAgent("DAOPessoas", new AbstractAction() {
			@Override
			public void act(Object target, Object callback) {
				agent = (Agent) target;
			}
		});
	}

	@Override
	public void criarBD() {
		if (!editando) {
			agent.see("criarBD", null);
		}
		else {
			agent.see("ask", "criarBD");
		}
	}

	@Override
	public void apagarBD() {
		if (!editando) {
			agent.see("apagarBD", null);
		}
		else {
			agent.see("ask", "apagarBD");
		}
	}

	@Override
	public Integer inserir(Pessoa bean) {
		AtomicInteger id = new AtomicInteger();
		if (!editando) {
			agent.see("inserir", bean, new AbstractAction() {
				public void act(Object target, Object callback) {
					id.set((Integer) target);
				}
			});
		}
		else {
			agent.see("ask", "inserir");
		}
		return id.get();
	}

	@Override
	public List<Pessoa> listar() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		if (!editando) {
			agent.see("listar", null, new AbstractAction() {
				@Override
				public void act(Object target, Object callback) {
					pessoas.addAll((List<Pessoa>) target);
				}
			});
		}
		else {
			agent.see("ask", "listar");
		}
		return pessoas;
	}

	@Override
	public void editar(Pessoa bean, Integer chave) {
		if (!editando) {
			agent.see("editar", new Object[] {bean, chave});
		}
		else {
			agent.see("ask", "editar");
		}
	}

	@Override
	public void apagar(Integer chave) {
		if (!editando) {
			agent.see("apagar", chave);
		}
		else {
			agent.see("ask", "apagar");
		}
	}

	@Override
	public Pessoa ver(Integer chave) {
		Pessoa pessoa = new Pessoa();
		if (!editando) {
			agent.see("ver", chave, new AbstractAction() {
				@Override
				public void act(Object target, Object callback) {
					Pessoa p = (Pessoa) target;
					pessoa.clonar(p);
				}
			});
		}
		else {
			agent.see("ask", "ver");
		}
		return pessoa;
	}

	@Override
	public List<Pessoa> listarPorNome(String nome) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		if (!editando) {
			agent.see("listarPorNome", nome, new AbstractAction() {
				@Override
				public void act(Object target, Object callback) {
					pessoas.addAll((List<Pessoa>) target);
				}
			});
		}
		else {
			agent.see("ask", "listarPorNome");
		}
		return pessoas;
	}
}
