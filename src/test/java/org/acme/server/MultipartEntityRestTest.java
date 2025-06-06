package org.acme.server;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.acme.model.MultipartEntity;
import org.acme.rest.client.MultipartEntityClient;
import org.acme.rest.mapper.MultipartEntityMultipartEntityDTO;
import org.acme.rest.server.MultipartEntityRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import jakarta.persistence.EntityManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MultipartEntityRestTest {
    private File tempFile;
    private final Long fakeId = 1L;

    @InjectMocks
    MultipartEntityRest rest;

    @Mock
    EntityManager manager;

    @Mock
    MultipartEntityMultipartEntityDTO mapper;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
        tempFile= new File("src/test/java/org/acme/server/files/test.txt");
        if(tempFile==null){
            throw new NotFoundException("Test error, fail not found!");
        }
    }

    @Test
    public void testUploadFile() throws IOException {

        MultipartEntityClient dto = new MultipartEntityClient();
        dto.fileName = "mocked.txt";
        dto.file = new ByteArrayInputStream("test content".getBytes());

        MultipartEntity fakeEntity = new MultipartEntity();
        when(manager.find(MultipartEntity.class, fakeId)).thenReturn(fakeEntity);

        Response response = rest.uploadFile(fakeId, dto);

        assertEquals(200, response.getStatus());
        verify(manager).merge(any(MultipartEntity.class));
    }

    @Test
    public void testDownloadFileSuccess() {
        MultipartEntity entity = new MultipartEntity();
        entity.setFilePath(tempFile.getAbsolutePath());

        when(manager.find(MultipartEntity.class, fakeId)).thenReturn(entity);

        Response response = rest.downloadFile(fakeId);

        assertEquals(200, response.getStatus());
        assertEquals("attachment; filename=\"" + tempFile.getName() + "\"",
                response.getHeaderString("Content-Disposition"));

        File responseFile = (File) response.getEntity();
        assertEquals(tempFile.getName(), responseFile.getName());
        assertTrue(responseFile.exists());
    }

    @Test
    public void testDownloadFileNotFoundInDb() {
        when(manager.find(MultipartEntity.class, fakeId)).thenReturn(null);

        Response response = rest.downloadFile(fakeId);

        assertEquals(404, response.getStatus());
        assertEquals("File not found", response.getEntity());
    }

    @Test
    public void testDownloadFilePathNull() {
        MultipartEntity entity = new MultipartEntity();
        entity.setFilePath(null);

        when(manager.find(MultipartEntity.class, fakeId)).thenReturn(entity);

        Response response = rest.downloadFile(fakeId);

        assertEquals(404, response.getStatus());
        assertEquals("File not found", response.getEntity());
    }

    @Test
    public void testDownloadFileNotOnDisk() {
        MultipartEntity entity = new MultipartEntity();
        entity.setFilePath("nonexistent-file.txt");

        when(manager.find(MultipartEntity.class, fakeId)).thenReturn(entity);

        Response response = rest.downloadFile(fakeId);

        assertEquals(404, response.getStatus());
        assertEquals("File not found on disk", response.getEntity());
    }
}
