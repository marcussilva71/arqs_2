package loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Produto;
import negocio.DAO;
import negocio.ServicoCategoria;
import negocio.ServicoProduto;
import br.unibh.loja.util.Resources;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteServicoProduto {

	@Deployment
	public static Archive<?> createTestArchive() {
		// Cria o pacote que vai ser instalado no Wildfly para realizacao dos testes
		return ShrinkWrap.create(WebArchive.class, "testeloja.war")
				.addClasses(Produto.class, Categoria.class, DAO.class, ServicoProduto.class, ServicoCategoria.class, Resources.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	// Realiza as injecoes com CDI
		@Inject
		private Logger log;
		
		@Inject
		private ServicoCategoria sc;
		
		@Inject
		private ServicoProduto sp;
		
		@Test
		public void teste00_inserirCategoriaSemErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Categoria o = new Categoria(null, "Categoria teste");
			sc.insert(o);
			Categoria aux = (Categoria) sc.findByName("Categoria teste").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
		public void teste01_inserirProdutoSemErro() throws Exception {
			System.out.println(log);
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Categoria c = (Categoria) sc.findByName("Categoria teste").get(0);
			Produto o = new Produto(null, "Produto", "Descricao", c, new BigDecimal(300), "Fabricante");
			sp.insert(o);
			Produto aux = (Produto) sp.findByName("Produto").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
		public void teste02_inserirProdutoComErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			try {
				Produto o = new Produto(null, "(Produto1@)", "Descricao1", null, new BigDecimal(-2), "Farbicante1");
				sp.insert(o);
			} catch (Exception e){
				assertTrue(checkString(e, "deve ser maior ou igual a 0"));
			}
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
			public void teste03_atualizarProduto() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Produto o = (Produto)  sp.findByName("Produto").get(0);
			o.setNome("Produto teste atualizar");
			sp.update(o);
			Produto aux = (Produto)  sp.findByName("Produto teste atualizar").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
			public void teste04_excluirProduto() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Produto o = (Produto) sp.findByName("Produto teste atualizar").get(0);
			sp.delete(o);
			assertEquals(0, sp.findByName("Produto teste atualizar").size());
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		private boolean checkString(Throwable e, String str){
		if (e.getMessage().contains(str)){
			return true;
			} else if (e.getCause() != null){
				return checkString(e.getCause(), str);
			}
			return false;
		}
}