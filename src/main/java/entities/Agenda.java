package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda implements Serializable{
	private static final long serialVersionUID = 1L;

	private static Agenda agenda;
	
	private List<Contato> lista;
	
	private Agenda() {
		setLista(new ArrayList<>());
	}
	
	public static Agenda CriarAgenda() {
		if(agenda == null) {
			Agenda ag = new Agenda();
			agenda = ag;
			return ag;
		} else {
			return agenda;
		}
	}

	public List<Contato> getLista() {
		return lista;
	}

	public void setLista(List<Contato> lista) {
		this.lista = lista;
	}
	
	public void modificarOrganizacaoContatos(FormaOrganizacaoContato formaOrganizacaoContato) {
		for(Contato c : lista) {
			c.setFormaOrganizacaoContato(formaOrganizacaoContato);
		}
	}
	
	public String RetornarContatos(FormaOrganizacaoContato formaOrganizacaoContato) {
		StringBuilder sb = new StringBuilder();
		modificarOrganizacaoContatos(formaOrganizacaoContato);
		Collections.sort(lista);
		for(Contato c : lista) {
			sb.append(c.toString());
		}
		
		return sb.toString();
	}
}
