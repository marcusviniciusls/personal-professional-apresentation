package br.com.marcus.dev.personal.professional.apresentation.services.subject.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import org.springframework.stereotype.Component;

@Component
public class SubjectFactory {

    public Subject convertSubjectFormSaveToEntity(SubjectFormSave subjectFormSave, Graduation graduation){
        Subject subject = new Subject();
        subject.setSituationSubject(SituationSubject.toEnum(subjectFormSave.getSituationSubject()));
        subject.setName(subjectFormSave.getName());
        subject.setNote(subjectFormSave.getNote());
        subject.setPeriod(subjectFormSave.getPeriod());
        subject.setQtdHours(subjectFormSave.getQtdHours());
        subject.setGraduation(graduation);
        if(subjectFormSave.getDescription() != null){
            subject.setDescription(subjectFormSave.getDescription());
        }
        if(subjectFormSave.getImageReportRecord() != null){
            subject.setImageReportRecord(subjectFormSave.getImageReportRecord());
        }
        return subject;
    }
}
