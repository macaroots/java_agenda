package br.ifce.agenda.teste;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ifce.agenda.DAOPessoas;
import br.ifce.agenda.DAOPessoasBD;
import br.ifce.agenda.DAOPessoasBDRenato;
import br.ifce.agenda.DAOPessoasList;
import br.ifce.agenda.Pessoa;


public class TesteAgenda {
	
	DAOPessoas dao;
	@Before
	public void iniciar() {
		/**
		 * Escolha o seu DAO e comente os outros
		 */
		this.dao = new DAOPessoasBDRenato();
		this.dao = new DAOPessoasBD();
		this.dao = new DAOPessoasList();
		
		this.dao.criarBD();
	}
	@After
	public void finalizar() {
		this.dao.apagarBD();
	}

	@Test
	public void testarInserirListar() {
		List<Pessoa> lista = this.dao.listar();
		
		Map<Integer, Pessoa> pessoas = this.inserirPessoas();
		
		List<Pessoa> listaDepois = this.dao.listar();
		
		assertTrue(listaDepois.size() > lista.size());

		for (Integer key: pessoas.keySet()) {
			Pessoa p = pessoas.get(key);
			assertTrue(!lista.contains(p));
			assertTrue(listaDepois.contains(p));
		}
	}
	@Test
	public void testarApagar() {
		Map<Integer, Pessoa> pessoas = this.inserirPessoas();
		for (Integer key: pessoas.keySet()) {
			List<Pessoa> lista = this.dao.listar();
			Pessoa p = pessoas.get(key);

			assertTrue(lista.contains(p));
			
			this.dao.apagar(key);
			
			List<Pessoa> listaDepois = this.dao.listar();

			// Exibe o resultado
			/*/
			System.out.println("\nApagando: " + p);
			System.out.println("Antes:  " + lista);
			System.out.println("Depois: " + listaDepois);
			/**/
			
			assertTrue(listaDepois.size() < lista.size());
			assertTrue(!listaDepois.contains(p));
		}
	}
	@Test
	public void testarVer() {
		Map<Integer, Pessoa> pessoas = this.inserirPessoas();
		for (Integer key: pessoas.keySet()) {
			Pessoa p = pessoas.get(key);
			Pessoa cadastrada = this.dao.ver(key);

			assertTrue(p.equals(cadastrada));
		}
	}
	@Test
	public void testarEditar() {
		Map<Integer, Pessoa> pessoas = this.inserirPessoas();
		for (Integer key: pessoas.keySet()) {
			Pessoa p = pessoas.get(key);
			Pessoa cadastrada = this.dao.ver(key);

			assertTrue(p.equals(cadastrada));
			
			p.setNome(p.getNome() + " Nova");
			p.setTelefone(p.getTelefone() + "987");
			this.dao.editar(p, key);
			
			Pessoa cadastradaDepois = this.dao.ver(key);


			// Exibe o resultado
			/*/
			System.out.println("\nEditando: " + p);
			System.out.println("Antes: " + cadastrada);
			System.out.println("Depois: " + cadastradaDepois);
			/**/
			
			assertTrue(p.getNome().equals(cadastradaDepois.getNome()));
			assertTrue(!cadastradaDepois.getNome().equals(cadastrada.getNome()));
			assertTrue(p.getTelefone().equals(cadastradaDepois.getTelefone()));
			assertTrue(!cadastradaDepois.getTelefone().equals(cadastrada.getTelefone()));
		}
	}
	
	@Test
	public void testarProcurar() {
		Map<Integer, Pessoa> pessoas = this.inserirPessoas();
		for (Integer key: pessoas.keySet()) {
			Pessoa p = pessoas.get(key);
			List<Pessoa> cadastradas = this.dao.listarPorNome(p.getNome());

			assertTrue(cadastradas.contains(p));
		}
	}
	
	public Map<Integer, Pessoa> inserirPessoas() {
		Map<Integer, Pessoa> pessoas = new HashMap<Integer, Pessoa>();
		List<Pessoa> ps = new ArrayList<Pessoa>();
		ps.add(new Pessoa("Ana", "123"));
		ps.add(new Pessoa("Bela", "231"));
		ps.add(new Pessoa("Carla", "312"));
		
		for (Pessoa p: ps) {
			int novoId = this.dao.inserir(p);
			pessoas.put(novoId, p);
		}
		
		return pessoas;
	}

}
