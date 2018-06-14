package br.unibh.loja.entidades;
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
public class TestValidationCategoria {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeValidacaoCategoria1() {
		Categoria k = new Categoria(1L, "descricao1");
		System.out.println(k);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(k);
		for (ConstraintViolation<Categoria> cat : constraintViolations) {
			System.out.println(" Erro de Validacao: " + cat.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoProduto2() {
		Categoria k = new Categoria(1L, "descricao1");
		System.out.println(k);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(k);
		for (ConstraintViolation<Categoria> cat : constraintViolations) {
			System.out.println(" Erro de Validacao: " + cat.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
}
