package br.ifce.agenda.visao.texto;
import java.util.List;

import br.ifce.agenda.Pessoa;
import br.ifce.agenda.controlador.Agenda;
import br.ifce.mvc.Visao;

public class ListaTexto extends Visao<Agenda> {

	public ListaTexto(Agenda controlador) {
		super(controlador);
	}

	@Override
	public String render() {
		try {
			List<Pessoa> pessoas = (List<Pessoa>) attributes.get("pessoas");
			System.out.println("ID\tNome\tTelefone");
			for (Pessoa p: pessoas) {
				System.out.println(p.getId() + "\t" + p.getNome() + "\t" + p.getTelefone());			
			}
		} finally {
			
		}
		return null;
	}

}
