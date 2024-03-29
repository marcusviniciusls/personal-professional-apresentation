package br.com.marcus.dev.personal.professional.apresentation.services.subject.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormOnlySave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SubjectFactory {

    @Autowired private ModelMapper modelMapper;

    public Subject convertSubjectFormSaveToEntity(SubjectFormSave subjectFormSave, Graduation graduation){
        Subject subject = new Subject();
        subject.setSituationSubject(SituationSubject.toEnum(subjectFormSave.getSituationSubject()));
        subject.setName(subjectFormSave.getName());
        subject.setNote(subjectFormSave.getNote());
        subject.setPeriod(subjectFormSave.getPeriod());
        subject.setQtdHours(subjectFormSave.getQtdHours());
        if(subjectFormSave.getDescription() != null){
            subject.setDescription(subjectFormSave.getDescription());
        }
        if(subjectFormSave.getImageReportRecord() != null){
            subject.setImageReportRecord(subjectFormSave.getImageReportRecord());
        }
        return subject;
    }

    public Subject convertSubjectFormSaveOnlyToEntity(SubjectFormOnlySave subjectFormOnlySave){
        Subject subject = new Subject();
        subject.setSituationSubject(SituationSubject.toEnum(subjectFormOnlySave.getSituationSubject()));
        subject.setName(subjectFormOnlySave.getName());
        subject.setNote(subjectFormOnlySave.getNote());
        subject.setQtdHours(subjectFormOnlySave.getQtdHours());
        if (subjectFormOnlySave.getDescription() != null){
            subject.setDescription(subjectFormOnlySave.getDescription());
        }
        if (subjectFormOnlySave.getPeriod() != null){
            subject.setPeriod(subjectFormOnlySave.getPeriod());
        }
        return subject;
    }

    public SubjectResponse convertEntityInResponse(Subject subject){
        SubjectResponse subjectResponse = modelMapper.map(subject, SubjectResponse.class);
        return subjectResponse;
    }

    public Subject convertUpdateFormInEntity(SubjectFormUpdate subjectFormUpdate, Subject subject){
        if (subjectFormUpdate.getName() != null && !subjectFormUpdate.getName().equals("")){
            subject.setName(subjectFormUpdate.getName());
        }
        if (subjectFormUpdate.getQtdHours() != null){
            subject.setQtdHours(subjectFormUpdate.getQtdHours());
        }
        if (subjectFormUpdate.getNote() != null){
            subject.setNote(subjectFormUpdate.getNote());
        }
        if (subjectFormUpdate.getDescription() != null && !subjectFormUpdate.getDescription().equals("")){
            subject.setDescription(subjectFormUpdate.getDescription());
        }
        if (subjectFormUpdate.getPeriod() != null && !subjectFormUpdate.getPeriod().equals("")){
            subject.setPeriod(subjectFormUpdate.getPeriod());
        }
        if (subjectFormUpdate.getSituationSubject() != null){
            subject.setSituationSubject(SituationSubject.toEnum(subjectFormUpdate.getSituationSubject()));
        }
        return subject;
    }
}
