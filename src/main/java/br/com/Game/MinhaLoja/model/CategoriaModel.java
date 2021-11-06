package br.com.Game.MinhaLoja.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// camada que indica que aqui é dedicado a criação da entidade tabela
@Entity
@Table(name = "tb_categoria")
public class CategoriaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategoria;

	@NotBlank(message = "Não pode ser nulo nem pode ser preenchido pela tecla espaço")
	@Size(min = 1, max = 100)
	private String genero;

	@NotBlank(message = "Não pode ser nulo nem pode ser preenchido pela tecla espaço")
	@Size(min = 1, max = 100)
	private String plataforma;

	@NotBlank(message = "Não pode ser nulo nem pode ser preenchido pela tecla espaço")
	@Size(min = 1, max = 100)
	private String condicao;

	// mappedby --> qual o lado inverso da operação
	// cascade --> o que uma tabela fizer, funcionará nas outras
	// @JsonIgnoreProperties --> deleta as repetições e só mostra onde está alocado

	// 1: N
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) // SELECT TABLE INNER JOIN -- ON
	@JsonIgnoreProperties("categoria")
	private List<ProdutoModel> produto;

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public List<ProdutoModel> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoModel> produto) {
		this.produto = produto;
	}

}
