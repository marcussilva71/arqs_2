package br.unibh.loja.integracao;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.loja.entidades.Produto;
import negocio.ServicoProduto;
import io.swagger.annotations.Api;

@Api
@Path("Produto")
public class RestProduto {
	@Inject
	private ServicoProduto sc;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> helloworld() throws Exception {
		return sc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto hello(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto postProduto(Produto p) throws Exception {
		return sc.insert(p);
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto putProduto(@PathParam("id") final Long id, Produto p) throws Exception{	
		return sc.update(p);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProduto(@PathParam("id") final Long id) throws Exception {
		Produto c = sc.find(id);
		sc.delete(c);
	}
	
}