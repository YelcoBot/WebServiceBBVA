package com.bbva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbva.dao.ClienteDAO;
import com.bbva.model.Cliente;

@RestController
@RequestMapping("api")
public class ClienteController {

	@Autowired
	private ClienteDAO clienteDAO;

	@GetMapping("/clientes")
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	@GetMapping("/clientes/{id}")
	public Cliente getCliente(@PathVariable int id) {
		Cliente clnt = clienteDAO.findById(id).orElse(null);
		if (clnt == null) {
			throw new RuntimeException("Cliente Id no encontrado -" + id);
		}
		return clnt;
	}

	@PostMapping("/clientes")
	public Cliente addCliente(@RequestBody Cliente clnt) {

		Cliente clntFilter = new Cliente();
		clntFilter.setTipo_documento(clnt.getTipo_documento());
		clntFilter.setNumero_documento(clnt.getNumero_documento());		
		Cliente clntExists = clienteDAO.findOne(Example.of(clntFilter)).orElse(null);
		
		if (clntExists!= null) {
			throw new RuntimeException("Ya existe un cliente con el mismo tipo y numero de documento : "
					+ clntExists.getTipo_documento().getCodigo() + " " + clntExists.getNumero_documento());
		}

		clnt.setId(0);
		clienteDAO.save(clnt);
		return clnt;
	}

	@PutMapping("/clientes")
	public Cliente updateCliente(@RequestBody Cliente clnt) {
		Cliente clntExists = clienteDAO.findById(clnt.getId()).orElse(null);
		if (clntExists == null) {
			throw new RuntimeException("Cliente Id no encontrado -" + clnt.getId());
		}

		clntExists.setTipo_documento(clnt.getTipo_documento());
		clntExists.setNumero_documento(clnt.getNumero_documento());
		clntExists.setNombre(clnt.getNombre());
		clntExists.setApellido(clnt.getApellido());
		clntExists.setSexo(clnt.getSexo());
		clntExists.setFecha_nacimiento(clnt.getFecha_nacimiento());
		clntExists.setDireccion(clnt.getDireccion());
		clntExists.setTelefono(clnt.getTelefono());
		clntExists.setEmail(clnt.getEmail());

		clienteDAO.save(clntExists);
		return clntExists;
	}

	@DeleteMapping("clientes/{id}")
	public String deteteCliente(@PathVariable int id) {
		Cliente clnt = clienteDAO.findById(id).orElse(null);
		if (clnt == null) {
			throw new RuntimeException("Cliente Id no encontrado -" + id);
		}
		clienteDAO.deleteById(id);
		return "Cliente Id eliminado - " + id;
	}
}
