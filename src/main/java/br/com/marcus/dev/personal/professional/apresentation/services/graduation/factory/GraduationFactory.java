package br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.factory.SubjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class GraduationFactory {

    @Autowired private SubjectFactory subjectFactory;

    public Graduation convertFormSaveToEntity(GraduationFormSave graduationFormSave){
        Graduation graduation = new Graduation();
        graduation.setTypeGraduation(TypeGraduation.toEnum(graduationFormSave.getTypeGraduation()));
        graduation.setSituationGraduation(SituationGraduation.toEnum(graduationFormSave.getSituationGraduation()));
        graduation.setDateFinishPreview(graduationFormSave.getDateFinishPreview());
        graduation.setDateFinishReal(graduationFormSave.getDateFinishReal());
        graduation.setName(graduationFormSave.getName());
        graduation.setDateInitPreview(graduationFormSave.getDateInitPreview());
        graduation.setDateInitReal(graduationFormSave.getDateInitReal());
        graduation.setLocation(graduationFormSave.getLocation());
        for (SubjectFormSave subjectFormSave: graduationFormSave.getListSubjectFormSave()){
            Subject subject = subjectFactory.convertSubjectFormSaveToEntity(subjectFormSave, graduation);
            graduation.addListSubject(subject);
        }

        return graduation;
    }

    private BigDecimal noteFinish(List<Subject> listSubject){
        List<BigDecimal> listNoteFinishBigDecimal = new ArrayList<>();
        listNoteFinishBigDecimal.addAll(listSubject);
        BigDecimal noteFinish = listNoteFinishBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return noteFinish;
    }
}
