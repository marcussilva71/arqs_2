package br.unibh.loja.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

	@Entity
	@Table(name="tb_categoria", uniqueConstraints = {
	    @UniqueConstraint(columnNames = { "id"})
	})
	
	@NamedQueries({
	@NamedQuery(name="Categoria.findByName", query = "select o from Produto o where o.nome like :nome")
	})
	
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;
	
	@Size(max=100)
	@Pattern(regexp="[A-zÀ-ú.´/ ]*", message="Caracteres permitidos: letras, espaços, ponto e aspas simples")
	@Column(length=4000, nullable=false)
	private String descricao;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descricao=" + descricao + ", hashCode()=" + hashCode() + ", getId()="
				+ getId() + ", getDescricao()=" + getDescricao() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
	public Categoria(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public Categoria() {
		
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
