package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindByIdFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImageGraduationService {

    @Autowired private FindByIdGraduationService findByIdGraduationService;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private DeleteFileService deleteFileService;

    public void deleteImageS3(UUID id){
        Graduation graduation = findByIdGraduationService.findByIdEntity(id);
        String urlImage = graduation.getUrlUniversityDegree();
        String keyName = getKeyFile.getKeyFile(urlImage);
        graduation.setUrlUniversityDegree("");
        graduation = (Graduation) centerEntityService.setDataToUpdate(graduation);
        graduationRepository.save(graduation);
        deleteFileService.deleteObjectS3(keyName);
    }
}
