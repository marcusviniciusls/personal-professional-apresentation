package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImageFrameworkService {

    @Autowired private FindByIdFrameworkService findByIdFrameworkService;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private DeleteFileService deleteFileService;

    public void deleteImageS3(UUID id){
        Framework framework = findByIdFrameworkService.findByIdEntity(id);
        String urlImage = framework.getUrlImage();
        String keyName = getKeyFile.getKeyFile(urlImage);
        framework.setUrlImage("");
        framework = (Framework) centerEntityService.setDataToUpdate(framework);
        frameworkRepository.save(framework);
        deleteFileService.deleteObjectS3(keyName);
    }
}
