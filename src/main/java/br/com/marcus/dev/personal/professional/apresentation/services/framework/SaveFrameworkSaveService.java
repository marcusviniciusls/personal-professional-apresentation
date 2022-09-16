package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveFrameworkSaveService {

    @Autowired private FindByIdFrameworkService findByIdFrameworkService;
    @Autowired private SendFileAwsS3 sendFileAwsS3;
    @Autowired private FrameworkRepository frameworkRepository;

    public void saveImageFramework(MultipartFile multipartFile, UUID idFramework){
        Framework framework = findByIdFrameworkService.findByIdEntity(idFramework);
        if (framework.getUrlImage() != null){
            throw new FileException("RESOURCE IS ALREADY SAVED");
        }
        String url = sendFileAwsS3.uploadFile(multipartFile).toString();
        framework.setUrlImage(url);
        frameworkRepository.save(framework);
    }
}
