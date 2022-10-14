package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImageSubjectService {

    @Autowired private FindByIdSubject findByIdSubject;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private DeleteFileService deleteFileService;

    public void deleteImageS3(UUID id){
        Subject subject = findByIdSubject.findByIdEntity(id);
        String urlImage = subject.getUrlImage();
        String keyName = getKeyFile.getKeyFile(urlImage);
        subject.setUrlImage("");
        subject = (Subject) centerEntityService.setDataToUpdate(subject);
        subjectRepository.save(subject);
        deleteFileService.deleteObjectS3(keyName);
    }
}
