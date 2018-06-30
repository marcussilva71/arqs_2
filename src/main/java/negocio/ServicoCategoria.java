package negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.loja.entidades.Categoria;

@Stateless
@LocalBean
public class ServicoCategoria {

	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;
	
	public Categoria insert(Categoria cat) throws Exception {
		log.info("Persistindo "+ cat);
		em.persist(cat);
		return cat;
	}
	
	public Categoria update(Categoria cat) throws Exception {
		log.info("Atualizando "+ cat);
		return em.merge(cat);
	}
	
	public void delete(Categoria cat) throws Exception {
		log.info("Removendo "+ cat);
		Object o = em.merge(cat);
		em.remove(o);
	}
	
	public Categoria find(Long cat1) throws Exception {
		log.info("Encontrando pela chave "+ cat1);
		return em.find(Categoria.class, cat1);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> findAll() throws Exception {
		log.info("Encontrando os objetos");
		return em.createQuery("from Categoria").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> findByName(String descricao) throws Exception {
		log.info("Encontrando o "+ descricao);
		return em.createNamedQuery("Categoria.findByName")
		.setParameter("descricao", "%"+descricao+"%").getResultList();
	}
}