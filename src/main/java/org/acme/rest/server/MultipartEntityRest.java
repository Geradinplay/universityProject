package org.acme.rest.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.MultipartEntity;
import org.acme.rest.mapper.MultipartEntityMultipartEntityDTO;
import org.acme.rest.client.MultipartEntityClient;
import org.acme.rest.userDTO.MultipartEntityDTO;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@ApplicationScoped
@Path("/entities")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class MultipartEntityRest {
    @Inject
    private EntityManager manager;
    @Inject
    private MultipartEntityMultipartEntityDTO mapper;

    @POST
    @Path("/{id}")
    @Transactional
    public Response uploadFile(@PathParam("id") Long id, @MultipartForm MultipartEntityClient dto) {
        System.out.println(id);
        if(id==null){
            return Response.status(400).entity("Error id is null!").build();
        }
        MultipartEntity entity = manager.find(MultipartEntity.class, id);//Автоматический JPA запрос в базу через EntityManager
        if(entity==null){
            entity= new MultipartEntity();
        }

        try {
            String uploadPath = "uploads/" + dto.fileName;
            File target = new File(uploadPath);
            target.getParentFile().mkdirs();

            try (InputStream is = dto.file;
                 OutputStream os = new FileOutputStream(target)) {
                is.transferTo(os);//копируют все биты с входа одного на выход другого
            }

            entity.setFilePath(uploadPath);
            manager.merge(entity);

            return Response.ok("File uploaded and path saved").build();
        } catch (IOException e) {
            return Response.serverError().entity("File saving failed").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntity(@PathParam("id") Long id) {
        MultipartEntity entity = manager.find(MultipartEntity.class, id);
        if (entity == null) return Response.status(404).build();

        MultipartEntityDTO dto = mapper.toDTO(entity);

        if (entity.getFilePath() == null) {
           return Response.serverError().entity("Error reading file").build();
        }

        return Response.ok().entity(dto).build();
    }

    @GET
    @Path("/download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("id") Long id) {
        MultipartEntity entity = manager.find(MultipartEntity.class, id);
        if (entity == null || entity.getFilePath() == null) {
            return Response.status(404).entity("File not found").build();
        }

        File file = new File(entity.getFilePath());
        if (!file.exists()) {
            return Response.status(404).entity("File not found on disk").build();
        }

        return Response.ok(file)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .build();
    }
}
