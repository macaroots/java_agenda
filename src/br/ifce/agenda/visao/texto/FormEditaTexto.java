package br.ifce.agenda.visao.texto;
import java.util.Scanner;

import br.ifce.agenda.Pessoa;
import br.ifce.agenda.controlador.Agenda;
import br.ifce.mvc.Visao;

public class FormEditaTexto extends Visao<Agenda> {

	public FormEditaTexto(Agenda controlador) {
		super(controlador);
	}

	@Override
	public String render() {
		Scanner sc = new Scanner(System.in);
		
		Pessoa p = (Pessoa) this.attributes.get("pessoa");
		
		System.out.println("Nome [" + p.getNome() + "]: ");
		String nome = sc.nextLine();
		System.out.println("Telefone [" + p.getTelefone() + "]: ");
		String telefone = sc.nextLine();

		if ("".equals(nome)) {
			nome = p.getNome();
		}
		if ("".equals(telefone)) {
			telefone = p.getTelefone();
		}

		p.setNome(nome);
		p.setTelefone(telefone);
		
		controlador.editar(p, p.getId());
		
		return null;
		
	}

}
