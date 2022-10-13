package com.bbva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbva.model.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

}
