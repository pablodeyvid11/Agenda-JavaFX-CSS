package entities;

import java.io.Serializable;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

//@Entity
public class Contato implements Comparable<Contato>, Serializable {
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String numero;
	private String email;
	private String operadora;
	private String grupo;

	public Contato() {
	}

	public Contato(Integer id, String nome, String numero, String email, String operadora, String grupo) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.email = email;
		this.operadora = operadora;
		this.grupo = grupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int compareTo(Contato o) {
		return this.id.compareTo(o.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contato:\n[id=");
		builder.append(id);
		builder.append(";,\nnome=");
		builder.append(nome);
		builder.append(";,\nnumero=");
		builder.append(numero);
		builder.append(";,\nemail=");
		builder.append(email);
		builder.append(";,\noperadora=");
		builder.append(operadora);
		builder.append(";,\ngrupo=");
		builder.append(grupo);
		builder.append("].");
		return builder.toString();
	}
}
