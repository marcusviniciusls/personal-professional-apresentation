package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.FindByIdGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.UpdateInformationGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.factory.SubjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateSubjectService {

    @Autowired private FindByIdGraduationService findByIdGraduationService;
    @Autowired private FindByIdSubject findByIdSubject;
    @Autowired private SubjectFactory subjectFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private UpdateInformationGraduationService updateInformationGraduationService;
    @Autowired private SubjectRepository subjectRepository;

    public SubjectResponse update(SubjectFormUpdate subjectFormUpdate, UUID idSubject){
        Subject subject = findByIdSubject.findByIdEntity(idSubject);
        subject = subjectFactory.convertUpdateFormInEntity(subjectFormUpdate, subject);
        subject = (Subject) centerEntityService.setDataToUpdate(subject);
        Graduation graduation = findByIdGraduationService.findByIdEntity(subjectFormUpdate.getGraduationId());
        if (!graduation.getId().equals(subjectFormUpdate.getGraduationId())){
            subject.setGraduation(graduation);
        }
        if (subjectFormUpdate.getNote() != null || subjectFormUpdate.getQtdHours() != null){
            graduation = updateInformationGraduationService.updateToSave(graduation);
        }
        subject = subjectRepository.save(subject);
        SubjectResponse subjectResponse = subjectFactory.convertEntityInResponse(subject);
        return subjectResponse;
    }
}
