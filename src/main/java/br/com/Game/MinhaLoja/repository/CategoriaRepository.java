package br.com.Game.MinhaLoja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Game.MinhaLoja.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> 
{
	public List<CategoriaModel> findAllByGeneroContainingIgnoreCase (String genero);
}
