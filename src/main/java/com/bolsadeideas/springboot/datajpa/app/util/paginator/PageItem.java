package com.bolsadeideas.springboot.datajpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

public class PageItem {

	private int numero;
	private boolean actual;
	
	@SuppressWarnings("unused")
	private List<PageItem> paginas;

	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
		
		paginas = new ArrayList<PageItem>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}

}
