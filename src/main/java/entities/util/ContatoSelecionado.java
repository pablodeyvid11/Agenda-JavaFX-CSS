package entities.util;

import entities.Contato;

public class ContatoSelecionado {
	private static ContatoSelecionado ctt;

	private Contato c;

	private ContatoSelecionado() {
	}

	public static ContatoSelecionado contatoSelecionado() {
		if (ctt == null) {
			ctt = new ContatoSelecionado();
		}
		return ctt;
	}

	public Contato getC() {
		return c;
	}

	public void setC(Contato c) {
		this.c = c;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
