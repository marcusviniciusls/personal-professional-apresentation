package br.com.marcus.dev.personal.professional.apresentation.config.amazon;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import java.io.File;

@Service
@NoArgsConstructor
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired private AmazonS3 s3Client;
    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath){
        try{
            File file = new File(localFilePath);
            LOG.info("Iniciando Upload");
            s3Client.putObject(bucketName, "teste", file);
            LOG.info("Finalizando Upload");
        } catch(AmazonServiceException amazonServiceException){
            LOG.info("amazonServiceException " + amazonServiceException.getErrorMessage());
            LOG.info("Status Code: " + amazonServiceException.getStatusCode());
            amazonServiceException.printStackTrace();
        } catch(AmazonClientException amazonClientException){
            LOG.info("amazonClientException " + amazonClientException.getMessage());
            amazonClientException.printStackTrace();
        }
    }
}
