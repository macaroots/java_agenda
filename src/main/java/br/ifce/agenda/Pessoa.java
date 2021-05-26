package br.ifce.agenda;

/**
 * @author Renato
 *
 */
public class Pessoa implements Cloneable {

	public Pessoa(String nome, String telefone) {
		setNome(nome);
		setTelefone(telefone);
	}
	public Pessoa(int id, String nome, String telefone) {
		setId(id);
		setNome(nome);
		setTelefone(telefone);
	}
	public Pessoa() {
	}
	public Pessoa clone() {
		try {
			return (Pessoa) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pessoa p = (Pessoa) obj;
		return this.getId() == p.getId() && this.getNome().equals(p.getNome()) && this.getTelefone().equals(p.getTelefone());
	}

	public int id;
	public String nome, telefone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString() {
		return getId() + " - " + getNome() + ": " + getTelefone();
	}
	public void clonar(Pessoa p) {
		this.setId(p.getId());
		this.setNome(p.getNome());
		this.setTelefone(p.getTelefone());
	}
	
}
