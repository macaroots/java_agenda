package br.ifce.agenda;
import java.util.ArrayList;
import java.util.List;

public class DAOPessoasList implements DAOPessoas {

	List<Pessoa> pessoas;
	public DAOPessoasList() {
		criarBD();
	}
	public void criarBD() {
		pessoas = new ArrayList<Pessoa>();
	}
	public void apagarBD() {
	}
	public Integer inserir(Pessoa pessoa) {
		pessoas.add(pessoa);
		int id = pessoas.size() - 1;
		pessoa.setId(id);
		return id;
	}
	public List<Pessoa> listar() {
		return new ArrayList<Pessoa>(pessoas);
	}
	public void editar(Pessoa bean, Integer chave) {
		pessoas.set(chave, bean);
	}
	public void apagar(Integer chave) {
		for (Pessoa p: pessoas) {
			if (p.getId() == chave) {
				pessoas.remove(p);
				break;
			}
		}
	}
	public Pessoa ver(Integer chave) {
		return (Pessoa) pessoas.get(chave).clone();
	}
	public List<Pessoa> listarPorNome(String nome) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (Pessoa p: this.pessoas) {
			if (nome.equals(p.getNome())) {
				pessoas.add(p.clone());
			}
		}
		return pessoas;
	}

}
