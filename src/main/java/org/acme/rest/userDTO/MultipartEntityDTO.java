package org.acme.rest.userDTO;

public class MultipartEntityDTO {
    public Long id;
    public String filePath;

    public MultipartEntityDTO(Long id, String filePath, String fileContentBase64) {
        this.id = id;
        this.filePath = filePath;
    }

    public MultipartEntityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}