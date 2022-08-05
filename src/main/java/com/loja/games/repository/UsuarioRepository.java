package com.loja.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.loja.games.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

	public List<Usuario> findAllByCPFContainingIgnoreCase(@Param("CPF")String CPF);

}
