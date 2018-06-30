package negocio;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.loja.entidades.Cliente;

@Stateless
@LocalBean
public class ServicoCliente {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;
	
	public Cliente insert(Cliente c) throws Exception {
		log.info("Persistindo "+ c);
		em.persist(c);
		return c;
	}
	
	public Cliente update(Cliente c) throws Exception {
		log.info("Atualizando "+ c);
		return em.merge(c);
	}
	
	public void delete(Cliente c) throws Exception {
		log.info("Removendo "+ c);
		Object o = em.merge(c);
		em.remove(o);
	}
	
	public Cliente find(Long c1) throws Exception {
		log.info("Encontrando pela chave "+ c1);
		return em.find(Cliente.class, c1);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws Exception {
		log.info("Encontrando os objetos");
		return em.createQuery("from Cliente").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findByName(String name) throws Exception {
		log.info("Encontrando o "+name);
		return em.createNamedQuery("Cliente.findByName")
		.setParameter("nome", "%"+name+"%").getResultList();
	}

}