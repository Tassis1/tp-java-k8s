package fr.tp;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class TaskResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public List<Task> list() {
        return em.createQuery("select t from Task t order by t.id", Task.class).getResultList();
    }

    @POST
    public Response create(Task task) {
        em.persist(task);
        return Response.status(201).entity(task).build();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        Task task = em.find(Task.class, id);
        if (task != null) {
            em.remove(task);
        }
    }
}