package br.ifce.agenda.visao.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import br.ifce.agenda.Pessoa;
import br.ifce.agenda.controlador.Agenda;
import br.ifce.mvc.Visao;

public class MenuGUI extends Visao<Agenda> {
	public MenuGUI(Agenda controlador) {
		super(controlador);
	}

	
	@Override
	public String render() {
		JFrame frame = new JFrame();

		JPanel pForm = new JPanel();
		JLabel label1, label2;
		label1 = new JLabel("Name: ");
		label2 = new JLabel("Phone: ");
		JTextField txName, txPhone;
		txName = new JTextField(20);
		txName.setName("nome");
		txPhone = new JTextField(20);
		txPhone.setName("matricula");
		JButton button1 = new JButton("Insert");
		JButton button2 = new JButton("List");

		JTable table = new JTable();
		JScrollPane scroll = new JScrollPane(table);
		DefaultTableModel model = new DefaultTableModel(new String [][] {}, new String [] {"ID", "Name", "Phone"});
		table.setModel(model);
		table.setFillsViewportHeight(true);
		scroll.setName("scroll");
				
		if (controlador.listaView == null) {
			ListaGUI listaView = new ListaGUI(controlador);
			listaView.setModel(model);
			controlador.listaView = listaView;
		}

		frame.setLayout(new BorderLayout());
		frame.add(pForm, BorderLayout.PAGE_START);
		pForm.setLayout(new FlowLayout());
		pForm.add(label1);
		pForm.add(txName);
		pForm.add(label2);
		pForm.add(txPhone);
		pForm.add(button1);
		pForm.add(button2);
		frame.add(scroll, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setSize(760, 512);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				controlador.sair();
			}
		});
		frame.setVisible(true);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.inserir(new Pessoa(txName.getText(), txPhone.getText()));
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.listar();
			}
		});
		
		return null;
	}
	
}