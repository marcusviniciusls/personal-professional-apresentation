package br.com.marcus.dev.personal.professional.apresentation.config.amazon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
public class SendFileAwsS3 {

    @Autowired private S3Service s3Service;

    public URI uploadFile(MultipartFile multipartFile){
        return s3Service.uploadFile(multipartFile);
    }
}
