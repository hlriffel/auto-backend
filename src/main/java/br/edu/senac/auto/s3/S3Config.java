package br.edu.senac.auto.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    @Conditional(value = UseS3StorageConditional.class)
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder.standard().build();
    }
}
