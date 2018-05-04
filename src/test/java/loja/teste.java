package loja;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;


public class teste {

	@Test
	public void testarCliente() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		assertEquals(c1.getCpf(), "cpf");
	
	}
	
	@Test
	public void testarCategoria() {
		Categoria cat1 = new Categoria(1L, "descricao");
		assertEquals(cat1.getDescricao(), "descricao");

	}
	
	@Test
	public void testarProduto() {
		Categoria cat1 = new Categoria(1L, "descricao");
		Produto p1 = new Produto(1L,"nome", "descricao", cat1, new BigDecimal(10000), "fabricante");
		assertEquals(p1.getFabricante(), "fabricante");
	}

}
