package com.bbva.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbva.model.TipoDocumento;

public interface TipoDocumentoDAO  extends JpaRepository<TipoDocumento,Integer>  {
	
}
