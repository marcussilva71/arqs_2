package loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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

import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.util.Resources;
import negocio.DAO;
import negocio.ServicoCliente;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteServicoCliente {

	@Deployment
	public static Archive<?> createTestArchive() {
		// Cria o pacote que vai ser instalado no Wildfly para realizacao dos testes
		return ShrinkWrap.create(WebArchive.class, "testeloja.war")
				.addClasses(Cliente.class, ServicoCliente.class,	DAO.class, Resources.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	// Realiza as injecoes com CDI
		@Inject
		private Logger log;
		
		@Inject
		private ServicoCliente sc;
		
		@Test
		public void teste01_inserirClienteSemErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Cliente o = new Cliente(null, "Cliente", "clientetre", "123456789", "perfil", "13143909617", "(31)09999-8888", "cliente@cliente.com", new Date(), new Date());
			sc.insert(o);
			Cliente aux = (Cliente) sc.findByName("Cliente").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
		public void teste02_inserirClienteComErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			try {
				Cliente o = new Cliente(null, "Cliente1", "cliénte", "123", "perfil1", "26.232-88", "3-1399998888", "cliente", new Date(), new Date());
			sc.insert(o);
			} catch (Exception e){
				assertTrue(checkString(e, "CPF inválido"));
			}
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
			public void teste03_atualizarCliente() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Cliente o = (Cliente) sc.findByName("Cliente").get(0);
			o.setNome("Pedro");
			sc.update(o);
			Cliente aux = (Cliente) sc.findByName("Pedro").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " +
			Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
		@Test
			public void teste04_excluirCliente() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Cliente o = (Cliente) sc.findByName("Pedro").get(0);
			sc.delete(o);
			assertEquals(0, sc.findByName("Pedro").size());
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