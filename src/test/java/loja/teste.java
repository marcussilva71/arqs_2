package loja;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.unibh.loja.entidades.Cliente;


public class teste {

	@Test
	public void PrintObject() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		System.out.println(c1);
	}
	
	@Test
	public void EqualsObject() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		Cliente c2 = new Cliente(1L, "nome1", "login1", "senha1", "perfil1", "cpf1", "telefone1", "email1", new Date(), new Date());
		Assert.assertTrue(c1.equals(c2));
	}
	
	@Test
	public void ToStringObject() {
		Cliente c1 = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date(), new Date());
		System.out.print(c1.toString());
	}
	
}
