package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImageLanguageProgrammerService {

    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private DeleteFileService deleteFileService;

    public void deleteImageS3(UUID id){
        LanguageProgrammer languageProgrammer = findByIdLanguageProgrammerService.findByIdEntity(id);
        String urlImage = languageProgrammer.getUrlImage();
        String keyName = getKeyFile.getKeyFile(urlImage);
        languageProgrammer.setUrlImage("");
        languageProgrammer = (LanguageProgrammer) centerEntityService.setDataToUpdate(languageProgrammer);
        languageProgrammerRepository.save(languageProgrammer);
        deleteFileService.deleteObjectS3(keyName);
    }
}