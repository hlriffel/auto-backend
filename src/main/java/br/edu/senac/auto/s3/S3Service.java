package br.edu.senac.auto.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {

    @Autowired(required = false)
    private AmazonS3 s3Client;

    @Value("${jsa.s3.bucket}")
    private String bucket;

    public void uploadFile(MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        s3Client.putObject(new PutObjectRequest(bucket, file.getOriginalFilename(), file.getInputStream(), metadata));
    }

    public void deleteFile(String key) {
        s3Client.deleteObject(new DeleteObjectRequest(bucket, key));
    }

    public S3Object getFile(String key) {
        return s3Client.getObject(new GetObjectRequest(bucket, key));
    }
}
