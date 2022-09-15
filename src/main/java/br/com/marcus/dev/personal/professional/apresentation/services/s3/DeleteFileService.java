package br.com.marcus.dev.personal.professional.apresentation.services.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DeleteFileService {

    @Autowired
    private AmazonS3 s3Client;
    @Value("${s3.bucket}")
    private String bucketName;

    public void deleteObjectS3(String idObject){
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, idObject));
        } catch (AmazonServiceException e){
            e.printStackTrace();
            throw new AmazonServiceException("Error delete file!!" + e.getClass());
        } catch (SdkClientException e){
            e.printStackTrace();
            throw new SdkClientException("Error delete file!!" + e.getClass());
        }
    }
}
