package com.syonet.crud.quarkus.rest;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import com.syonet.crud.quarkus.rest.dto.CreateLibraryRequest;
import com.syonet.crud.quarkus.rest.dto.SelecionaLibrary;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;


@Path("/library")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class crudLibrary {
    /*  TESTE QUE REALIZEI NO CRUD ANTES DE IMPLEMENTAR;    
    @POST
    @Transactional
    public Response CreatLibrary(CreateLibraryRequest libraryRequest) {
        SelecionaLibrary library = new SelecionaLibrary();
        library.setAno(libraryRequest.getAno());
        library.setEmail(libraryRequest.getEmail());
        library.setTitulo(libraryRequest.getTitulo());

        library.persist();

        return Response.ok(library).build();
    }

    @GET
    public Response listAlllibrary() {
        PanacheQuery<SelecionaLibrary> listaLivros = SelecionaLibrary.findAll();
        return Response.ok(listaLivros.list()).build();
    }
*/
    
//Atualizando os cadastros dos livros;
@PUT
@Path("/{id}")
@Transactional
public Response updateLibrary(@PathParam("id") Long id, CreateLibraryRequest libraryRequest) {
    SelecionaLibrary library = SelecionaLibrary.findById(id);
    if (library == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    library.setTitulo(libraryRequest.getTitulo());
    library.setEmail(libraryRequest.getEmail());
    library.setAno(libraryRequest.getAno());
    
    library.persist();
    return Response.ok(library).build();
}

//Método Get com filtros
@GET
@Path("/filter")
public Response filterLibrary(@QueryParam("titulo") String titulo, @QueryParam("email") String email, @QueryParam("ano") Integer ano) {
   String query = "titulo like ?1 and email like ?2";
    List<Object> params = new ArrayList<>();
    params.add("%" + (titulo != null ? titulo : "") + "%");
    params.add("%" + (email != null ? email : "") + "%");

    if (ano != null) {
        query += " and ano = ?3";
        params.add(ano);
    }

    PanacheQuery<SelecionaLibrary> libraryQuery = SelecionaLibrary.find(query, params.toArray());
    return Response.ok(libraryQuery.list()).build();
}

//Deletando registro
@DELETE
@Path("/{id}")
@Transactional
public Response deleteLibrary(@PathParam("id") Long id) {
    boolean deleted = SelecionaLibrary.deleteById(id);
    if (!deleted) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.noContent().build();
}

//Buscar por id
@GET
@Path("/{id}")
public Response getLibraryById(@PathParam("id") Long id) {
    SelecionaLibrary library = SelecionaLibrary.findById(id);
    if (library == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(library).build();
}

@POST
@Transactional
public Response createLibrary(CreateLibraryRequest libraryRequest) {
    SelecionaLibrary library = new SelecionaLibrary();
    library.setAno(libraryRequest.getAno());
    library.setEmail(libraryRequest.getEmail());
    library.setTitulo(libraryRequest.getTitulo());
    library.persist();
    return Response.ok(library).build();
}

@GET
public Response listAllLibrary() {
    PanacheQuery<SelecionaLibrary> listaLivros = SelecionaLibrary.findAll();
    return Response.ok(listaLivros.list()).build();
}


}
