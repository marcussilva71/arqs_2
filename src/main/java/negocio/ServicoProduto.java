package negocio;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.loja.entidades.Produto;

@Stateless
@LocalBean
public class ServicoProduto {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;
	
	public Produto insert(Produto p) throws Exception {
		log.info("Persistindo "+ p);
		em.persist(p);
		return p;
	}
	
	public Produto update(Produto p) throws Exception {
		log.info("Atualizando "+ p);
		return em.merge(p);
	}
	
	public void delete(Produto p) throws Exception {
		log.info("Removendo "+ p);
		Object c = em.merge(p);
		em.remove(c);
	}
	
	public Produto find(Long p2) throws Exception {
		log.info("Encontrando pela chave "+ p2);
		return em.find(Produto.class, p2);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> findAll() throws Exception {
		log.info("Encontrando os objetos");
		return em.createQuery("from Produto").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> findByName(String name) throws Exception {
		log.info("Encontrando o "+ name);
		return em.createNamedQuery("Produto.findByName")
		.setParameter("nome", "%"+name+"%").getResultList();
	}
	
}