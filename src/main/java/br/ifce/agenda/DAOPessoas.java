package br.ifce.agenda;

import java.util.List;

import br.ifce.mvc.DAO;

public interface DAOPessoas extends DAO<Pessoa, Integer> {

	List<Pessoa> listarPorNome(String nome);

}