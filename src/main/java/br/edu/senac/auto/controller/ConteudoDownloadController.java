package br.edu.senac.auto.controller;

import br.edu.senac.auto.s3.S3Service;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/conteudo")
@CrossOrigin(origins = "*")
public class ConteudoDownloadController {

    @Autowired(required = false)
    private S3Service s3Service;

    @GetMapping(value = "/pdf/{key}", produces = "application/pdf")
    public ResponseEntity<Resource> getPdfFile(@PathVariable String key) {
        S3Object pdfObject = s3Service.getFile(key);

        if (pdfObject != null) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                headers.add(HttpHeaders.PRAGMA, "no-cache");
                headers.add(HttpHeaders.EXPIRES, "0");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + key + "\"");

                ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(pdfObject.getObjectContent()));

                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (IOException ex) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/video/{key}", produces = "video/*")
    public ResponseEntity<byte[]> getVideoFile(@PathVariable String key) {
        S3Object pdfObject = s3Service.getFile(key);

        if (pdfObject != null) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + key + "\"");

                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(IOUtils.toByteArray(pdfObject.getObjectContent()));
            } catch (IOException ex) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
