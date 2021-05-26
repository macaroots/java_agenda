package br.ifce.agenda.controlador;

import br.ifce.agenda.DAOPessoas;
import br.ifce.agenda.DAOPessoasList;
import br.ifce.agenda.Pessoa;
import br.ifce.agenda.visao.texto.FormEditaTexto;
import br.ifce.agenda.visao.texto.FormTexto;
import br.ifce.agenda.visao.texto.ListaTexto;
import br.ifce.agenda.visao.texto.MenuTexto;
import br.ifce.agenda.visao.gui.ListaGUI;
import br.ifce.agenda.visao.gui.MenuGUI;
import br.ifce.mvc.Visao;

public class Agenda {

	public DAOPessoas crud;
	public Visao menuView, listaView, formView, formEditaView;
	public Agenda() {
		/**
		 *  Escolha o crud
		 */
		this.crud = new DAOPessoasList();
		
		/**
		 * Escolha a visualização do menu
		 */
		/**/
		this.menuView = new MenuGUI(this);
		/*/
		this.menuView = new MenuTexto(this);
		/**/
		
		/**
		 *  Escolha a visualização da lista (null para GUI)
		 */
		/**/
		this.listaView = null;
		/*/
		this.listaView = new ListaTexto(this);
		/**/
		
		this.formView = new FormTexto(this);
		this.formEditaView = new FormEditaTexto(this);
		
		menu();
	}
	public void menu () {
		menuView.render();
	}
	public void form() {
		formView.render();
	}

	public void form(int id) {
		Pessoa pessoa = crud.ver(id);
		this.formEditaView.attributes.put("pessoa", pessoa);
		formEditaView.render();
	}

	public void inserir(Pessoa pessoa) {
		int id = this.crud.inserir(pessoa);
		System.out.println("inserido: " + id);
		this.listar();
	}
	
	public void listar() {
		this.listaView.attributes.put("pessoas", crud.listar());
		listaView.render();
	}
	
	public void editar(Pessoa p, int id) {
		this.crud.editar(p, id);
		this.listar();
	}
	
	public void sair() {
		System.out.println("Tchau!");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new Agenda();
	}
	public void apagar(int id) {
		this.crud.apagar(id);
		this.listar();
	}
}
