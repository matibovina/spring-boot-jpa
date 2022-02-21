package com.bolsadeideas.springboot.datajpa.app.view.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;

@XmlRootElement(name = "clientes")
public class ClienteList {

	@XmlElement(name = "cliente")
	public List<Cliente> clientes;

	public ClienteList(List<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	public ClienteList() {

	}

	public List<Cliente> getClientes() {
		return clientes;
	}

}
