package br.unibh.loja.entidades;
import java.math.BigDecimal;
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
public class TestValidationProduto {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeValidacaoProduto1() {
		Produto p = new Produto(1L, "nome1", "descricao1", new Categoria(), new BigDecimal(20), "telefone1");
		System.out.println(p);
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p);
		for (ConstraintViolation<Produto> pr : constraintViolations) {
			System.out.println(" Erro de Validacao: " + pr.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoProduto2() {
		Produto p = new Produto(1L, "nome2", "descricao2", new Categoria(), new BigDecimal(20), "telefone2");
		System.out.println(p);
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p);
		for (ConstraintViolation<Produto> pr : constraintViolations) {
			System.out.println(" Erro de Validacao: " + pr.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
}
