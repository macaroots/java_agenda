package br.ifce.agenda.visao.texto;
import java.util.Scanner;

import br.ifce.agenda.DAOPessoas;
import br.ifce.agenda.controlador.Agenda;
import br.ifce.mvc.Visao;

public class MenuTexto extends Visao<Agenda> {

	public MenuTexto(Agenda controlador) {
		super(controlador);
	}

	@Override
	public String render() {
		int id;
		while (true) {
			System.out.println("1. Cadastrar\n2. Listar\n3. Editar\n4. Apagar\n5. Sair");

			DAOPessoas dao = null;
// 			if (controlador.crud instanceof DAOPessoasAgent) {
// 				dao = (DAOPessoasAgent) controlador.crud;
// 				System.out.println("6. Alterar modo. Editando: " + dao.editando);
// 			}
			Scanner sc = new Scanner(System.in);
			int opcao = new Integer(sc.next());
			switch (opcao) {
			case 1:
				controlador.form();
				break;
			case 2:
				controlador.listar();
				break;
			case 3:
				controlador.listar();
				System.out.println("Escolha um ID pra editar:");
				id = sc.nextInt();
				controlador.form(id);
				break;
			case 4:
				controlador.listar();
				System.out.println("Escolha um ID pra apagar:");
				id = sc.nextInt();
				controlador.apagar(id);
				break;
			case 5:
				sc.close();
				controlador.sair();
				break;
			case 6:
				//dao.editando = !dao.editando;
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

}
