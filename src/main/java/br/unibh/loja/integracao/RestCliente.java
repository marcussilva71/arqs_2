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
import br.unibh.loja.entidades.Cliente;
import negocio.ServicoCliente;
import io.swagger.annotations.Api;

@Api
@Path("Cliente")
public class RestCliente {
	@Inject
	private ServicoCliente sc;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> helloworld() throws Exception {
		return sc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente hello(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente postClient(Cliente c) throws Exception {
		return sc.insert(c);
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente putClient(@PathParam("id") final Long id, Cliente c) throws Exception{	
		return sc.update(c);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteClient(@PathParam("id") final Long id) throws Exception {
		Cliente c = sc.find(id);
		sc.delete(c);
	}
}