package com.bbva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbva.dao.TipoDocumentoDAO;
import com.bbva.model.TipoDocumento;

@RestController
@RequestMapping("api")
public class TipoDocumentoController {

	@Autowired
	private TipoDocumentoDAO documentoDAO;

	@GetMapping("/documentos")
	public List<TipoDocumento> findAll() {
		return documentoDAO.findAll();
	}

	@GetMapping("/documentos/{id}")
	public TipoDocumento getDocumento(@PathVariable int id) {
		TipoDocumento doc = documentoDAO.findById(id).orElse(null);
		if (doc == null) {
			throw new RuntimeException("Documento Id no encontrado -" + id);
		}
		return doc;
	}
}
