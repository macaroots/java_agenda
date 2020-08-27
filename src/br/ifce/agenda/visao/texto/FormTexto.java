package br.ifce.agenda.visao.texto;
import java.util.Scanner;

import br.ifce.agenda.Pessoa;
import br.ifce.agenda.controlador.Agenda;
import br.ifce.mvc.Visao;

public class FormTexto extends Visao<Agenda> {

	public FormTexto(Agenda controlador) {
		super(controlador);
	}

	@Override
	public String render() {
		Scanner sc = new Scanner(System.in);
		
		Pessoa p = new Pessoa();
		
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("Telefone: ");
		String telefone = sc.nextLine();

		p.setNome(nome);
		p.setTelefone(telefone);
		
		controlador.inserir(p);
		
		return null;
		
	}

}
