package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveImageLanguageProgrammerService {

    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;
    @Autowired private SendFileAwsS3 sendFileAwsS3;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    public void saveImage(MultipartFile multipartFile, UUID idLanguageProgrammer){
        LanguageProgrammer languageProgrammer = findByIdLanguageProgrammerService.findByIdEntity(idLanguageProgrammer);
        if (languageProgrammer.getUrlImage() != null && !languageProgrammer.getUrlImage().equals("")){
            throw new FileException("RESOURCE IS ALREADY SAVED");
        }
        String url = sendFileAwsS3.uploadFile(multipartFile).toString();
        languageProgrammer.setUrlImage(url);
        languageProgrammerRepository.save(languageProgrammer);
    }
}
