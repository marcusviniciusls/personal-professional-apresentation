package br.com.marcus.dev.personal.professional.apresentation.config.amazon;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@NoArgsConstructor
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired private AmazonS3 s3Client;
    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile){
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            String contextType = multipartFile.getContentType();
            return uploadFile(inputStream, fileName, contextType);
        } catch (IOException ioException){
            throw new FileException("ERROR IO: " + ioException.getMessage());
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String contextType){
        try{
            Random random = new Random();
            fileName = random.nextInt() + fileName;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contextType);
            LOG.info("Iniciando Upload");
            s3Client.putObject(bucketName, fileName, inputStream, objectMetadata);
            LOG.info("Finalizando Upload");
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch(URISyntaxException uriSyntaxException){
            throw new FileException("error converting URL to URI");
        }
    }
}
