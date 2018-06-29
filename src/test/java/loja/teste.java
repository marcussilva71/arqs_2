package loja;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;


public class teste {

	@Test
	public void PrintObject() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		System.out.println(c1);
		
		Categoria cat1 = new Categoria(1L, "descricao");
		
		Produto p1 = new Produto(1L, "nome1", "descricao1", cat1, new BigDecimal(20), "telefone1");
		System.out.println(p1);
	}
	
	@Test
	public void EqualsObject() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		Cliente c2 = new Cliente(1L, "nome1", "login1", "senha1", "perfil1", "cpf1", "telefone1", "email1", new Date(), new Date());
		Assert.assertTrue(c1.equals(c2));
		
		Categoria cat1 = new Categoria(1L, "descricao");
		Categoria cat2 = new Categoria(1L, "descricao1");
		Assert.assertTrue(cat1.equals(cat2));
		
		Produto p1 = new Produto(1L, "nome1", "descricao1", cat1, new BigDecimal(20), "telefone1");
		Produto p2 = new Produto(1L, "nome2", "descricao2", cat2, new BigDecimal(20), "telefone2");
		Assert.assertTrue(p1.equals(p2));
	}
	
	@Test
	public void ToStringObject() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		System.out.print(c1.toString());
		
		Categoria cat1 = new Categoria(1L, "descricao");
		System.out.print(cat1.toString());
		
		Produto p1 = new Produto(1L, "nome1", "descricao1", cat1, new BigDecimal(20), "telefone1");
		System.out.print(p1.toString());
	}
}
