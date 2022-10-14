package br.com.marcus.dev.personal.professional.apresentation.services.s3;

import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteFileServiceTest {

    @MockBean private DeleteFileService deleteFileServiceMockado;
    @Autowired private DeleteFileService deleteFileService;

    @Test
    @Transactional
    @DisplayName("Apagar arquivo do S3")
    public void deleteObjectS3(){
        BDDMockito.given(deleteFileServiceMockado.deleteObjectS3(Mockito.any(String.class))).willReturn(true);
        Assertions.assertTrue(deleteFileService.deleteObjectS3(""));
    }

    @Test
    @Transactional
    @DisplayName("Apagar arquivo do S3, mas com erro de AmazonServiceException")
    public void deleteObjectS3AmazonServiceException(){
        BDDMockito.given(deleteFileServiceMockado.deleteObjectS3(Mockito.any(String.class))).willThrow(AmazonServiceException.class);
        Assertions.assertThrows(AmazonServiceException.class, () -> deleteFileService.deleteObjectS3(""));
    }

    @Test
    @Transactional
    @DisplayName("Apagar arquivo do S3, mas com erro de SdkClientException")
    public void deleteObjectS3SdkClientException(){
        BDDMockito.given(deleteFileServiceMockado.deleteObjectS3(Mockito.any(String.class))).willThrow(SdkClientException.class);
        Assertions.assertThrows(SdkClientException.class, () -> deleteFileService.deleteObjectS3(""));
    }
}
