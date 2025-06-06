package org.acme.rest.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.rest.userDTO.TextFileMultipartDTO;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@ApplicationScoped
@Path("/upload")
public class TextFileMultipartClient {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFromUrl(@MultipartForm TextFileMultipartDTO dto) {
        try {
            String uploadDirPath = "src/main/java/org/acme/tmp"; // относительный путь от корня проекта
            File uploadDir = new File(uploadDirPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // создаём, если нет
            }
            // Куда сохранить файл
            File targetFile = new File(uploadDir ,dto.fileName);

            // Сохраняем файл
            try (InputStream in = dto.file; OutputStream out = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            return Response.ok("File uploaded successfully: " + dto.fileName).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Upload failed: " + e.getMessage()).build();
        }
    }


}
