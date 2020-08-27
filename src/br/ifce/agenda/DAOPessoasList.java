package br.ifce.agenda;
import java.util.ArrayList;
import java.util.List;

import br.ifce.mvc.DAO;

public class DAOPessoasList implements DAOPessoas {

	List<Pessoa> pessoas;
	public DAOPessoasList() {
		criarBD();
	}
	/* (non-Javadoc)
	 * @see br.ifce.agenda.DAOPessoas#criaDB()
	 */
	@Override
	public void criarBD() {
		pessoas = new ArrayList<Pessoa>();
	}
	/* (non-Javadoc)
	 * @see br.ifce.agenda.DAOPessoas#apagaDB()
	 */
	@Override
	public void apagarBD() {
	}
	/* (non-Javadoc)
	 * @see br.ifce.agenda.DAOPessoas#insere(br.ifce.agenda.Pessoa)
	 */
	@Override
	public Integer inserir(Pessoa pessoa) {
		pessoas.add(pessoa);
		int id = pessoas.size() - 1;
		pessoa.setId(id);
		return id;
	}

	/* (non-Javadoc)
	 * @see br.ifce.agenda.DAOPessoas#lista()
	 */
	@Override
	public List<Pessoa> listar() {
		return new ArrayList<Pessoa>(pessoas);
	}
	/* (non-Javadoc)
	 * @see br.ifce.agenda.DAOPessoas#altera(br.ifce.agenda.Pessoa, java.lang.Integer)
	 */
	@Override
	public void editar(Pessoa bean, Integer chave) {
		pessoas.set(chave, bean);
	}
	/* (non-Javadoc)
	 * @see br.ifce.agenda.DAOPessoas#apaga(java.lang.Integer)
	 */
	@Override
	public void apagar(Integer chave) {
		for (Pessoa p: pessoas) {
			if (p.getId() == chave) {
				pessoas.remove(p);
				break;
			}
		}
	}
	@Override
	public Pessoa ver(Integer chave) {
		return (Pessoa) pessoas.get(chave).clone();
	}
	@Override
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
