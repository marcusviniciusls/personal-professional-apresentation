package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormOnlySave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.FindByIdGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.UpdateInformationGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.factory.SubjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaveSubjectService {

    @Autowired private FindByIdGraduationService findByIdGraduationService;
    @Autowired private SubjectFactory subjectFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private GraduationFactory graduationFactory;
    @Autowired private UpdateInformationGraduationService updateInformationGraduationService;

    public SubjectResponse save(SubjectFormOnlySave subjectFormOnlySave){
        Graduation graduation = findByIdGraduationService.findByIdEntity(subjectFormOnlySave.getGraduationId());
        Subject subject = subjectFactory.convertSubjectFormSaveOnlyToEntity(subjectFormOnlySave);
        subject.setGraduation(graduation);
        subject = (Subject) centerEntityService.setDataToSave(subject);
        subject = subjectRepository.save(subject);
        graduation.addListSubject(subject);
        graduation = updateInformationGraduationService.updateToSave(graduation);
        subject.setGraduation(graduation);
        SubjectResponse subjectResponse = subjectFactory.convertEntityInResponse(subject);
        return subjectResponse;
    }
}