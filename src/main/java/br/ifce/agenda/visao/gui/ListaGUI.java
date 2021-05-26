package br.ifce.agenda.visao.gui;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.ifce.agenda.Pessoa;
import br.ifce.agenda.controlador.Agenda;
import br.ifce.mvc.Visao;

public class ListaGUI extends Visao<Agenda> {

	DefaultTableModel model;
	public ListaGUI(Agenda controlador) {
		super(controlador);
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	@Override
	public String render() {
		model.setRowCount(0);
		List<Pessoa> pessoas = (List<Pessoa>) attributes.get("pessoas");
		for (Pessoa p: pessoas) {
			model.addRow(new Object [] {p.getId(), p.getNome(), p.getTelefone()});
			
		}
		return null;
	}

}
