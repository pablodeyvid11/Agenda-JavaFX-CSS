package entities;

import java.io.Serializable;

public class Contato implements Comparable<Contato>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Double ddd;
	private Double numero;
	private FormaOrganizacaoContato formaOrganizacaoContato;
	private Integer Id;

	public Contato(Integer Id, String nome, Double ddd, Double numero) {
		this.Id = Id;
		setFormaOrganizacaoContato(FormaOrganizacaoContato.nome);
		this.nome = nome;
		this.ddd = ddd;
		this.numero = numero;
	}

	public Integer getId() {
		return Id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getDdd() {
		return ddd;
	}

	public void setDdd(Double ddd) {
		this.ddd = ddd;
	}

	public Double getNumero() {
		return numero;
	}

	public void setNumero(Double numero) {
		this.numero = numero;
	}

	public FormaOrganizacaoContato getFormaOrganizacaoContato() {
		return formaOrganizacaoContato;
	}

	public void setFormaOrganizacaoContato(FormaOrganizacaoContato formaOrganizacaoContato) {
		this.formaOrganizacaoContato = formaOrganizacaoContato;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + Id);
		sb.append("%nNome: " + getNome());
		sb.append("%nNúmero: (" + getDdd() + ") " + (String.format("%.0f", getNumero())) + "%n");
		return sb.toString();
	}

	@Override
	public int compareTo(Contato o) {
		if (formaOrganizacaoContato == FormaOrganizacaoContato.nome) {
			return getNome().compareTo(o.getNome());
		} else if (formaOrganizacaoContato == FormaOrganizacaoContato.numero) {
			return getNumero().compareTo(o.getNumero());
		} else {
			return getId().compareTo(o.getId());
		}
	}
}
