package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.FindByIdGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.UpdateInformationGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteSubjectService {

    @Autowired private FindByIdSubject findByIdSubject;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private FindByIdGraduationService findByIdGraduationService;
    @Autowired private UpdateInformationGraduationService updateInformationGraduationService;
    @Autowired private GraduationRepository graduationRepository;

    public void delete(UUID id){
        Subject subject = findByIdSubject.findByIdEntity(id);
        try {
            subjectRepository.deleteById(id);
            refreshGraduation(subject);
        } catch(DataIntegrityViolationException ex){
            subject = (Subject) centerEntityService.setDataToDelete(subject);
            refreshGraduation(subject);
            subjectRepository.save(subject);
        }
    }

    private void refreshGraduation(Subject subject){
        Graduation graduation = findByIdGraduationService.findByIdEntity(subject.getGraduation().getId());
        if (graduation.getListSubject().size() == 1){
            graduation.setNoteFinish(BigDecimal.ZERO);
            graduation.setQtdHours(BigDecimal.ZERO);
            graduation.getListSubject().remove(subject);
        } else {
            graduation.getListSubject().remove(subject);
            graduation = (Graduation) centerEntityService.setDataToSave(graduation);
            graduation = updateInformationGraduationService.updateToSave(graduation);
            graduationRepository.save(graduation);
        }
    }
}
