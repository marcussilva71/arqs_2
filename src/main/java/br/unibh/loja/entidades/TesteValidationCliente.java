package br.unibh.loja.entidades;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteValidationCliente {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeValidacaoCliente1() {
		Cliente c = new Cliente(1L, "nome", "login", "senha", "perfil", "cpf", "telefone", "email", new Date (), new Date ());
		System.out.println(c);
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(c);
		for (ConstraintViolation<Cliente> cl : constraintViolations) {
			System.out.println(" Erro de Validacao: " + cl.getMessage());
		}
		Assert.assertEquals(3, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoCliente2() {
		Cliente c = new Cliente(2L, "nome1", "login1", "senha1", "perfil1", "cpf1", "telefone1", "email1", new Date(), new Date() );
		System.out.println(c);
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(c);
		for (ConstraintViolation<Cliente> cl : constraintViolations) {
			System.out.println(" Erro de Validacao: " + cl.getMessage());
		}
		Assert.assertEquals(5, constraintViolations.size());
	}
}
