package br.ifce.mvc;

import java.util.List;

public interface DAO<T, K> {
	public void criarBD();
	public void apagarBD();
	public K inserir(T bean);
	public List<T> listar();
	public void editar(T bean, K chave);
	public void apagar(K chave);
	public T ver(K chave);
}
