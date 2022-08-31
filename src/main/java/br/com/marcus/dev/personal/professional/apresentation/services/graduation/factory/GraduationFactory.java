package br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.factory.SubjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GraduationFactory {

    @Autowired private SubjectFactory subjectFactory;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private GraduationRepository graduationRepository;

    public Graduation convertFormSaveToEntity(GraduationFormSave graduationFormSave){
        Graduation graduation = new Graduation();
        graduation.setTypeGraduation(TypeGraduation.toEnum(graduationFormSave.getTypeGraduation()));
        graduation.setSituationGraduation(SituationGraduation.toEnum(graduationFormSave.getSituationGraduation()));
        graduation.setDateFinishPreview(graduationFormSave.getDateFinishPreview());
        graduation.setName(graduationFormSave.getName());
        graduation.setDateInitPreview(graduationFormSave.getDateInitPreview());
        graduation.setLocation(graduationFormSave.getLocation());
        graduation.setUrlUniversityDegree(graduationFormSave.getUrlUniversityDegree());
        if (graduationFormSave.getDateFinishReal() != null && SituationGraduation.CONCLUSION.getNumber() == 0){
            graduation.setDateFinishReal(graduationFormSave.getDateFinishReal());
        }
        if (graduationFormSave.getDateInitReal() != null && SituationGraduation.CONCLUSION.getNumber() == 0){
            graduation.setDateInitReal(graduationFormSave.getDateInitReal());
        }
        graduation.setNoteFinish(getNoteFinish(graduation.getListSubject()));
        graduation.setQtdHours(getQtdHours(graduation.getListSubject()));

        Partner partner = findByIdPartnerService.findByIdPartner(UUID.fromString(graduationFormSave.getPartnerId()));
        graduation.setPartner(partner);
        graduation = (Graduation) centerEntityService.setDataToSave(graduation);
        graduationRepository.save(graduation);

        for (SubjectFormSave subjectFormSave: graduationFormSave.getListSubjectFormSave()){
            Subject subject = subjectFactory.convertSubjectFormSaveToEntity(subjectFormSave, graduation);
            subject = (Subject) centerEntityService.setDataToSave(subject);
            subject.setGraduation(graduation);
            subject = subjectRepository.save(subject);
        }
        return graduation;
    }

    private BigDecimal getNoteFinish(List<Subject> listSubject){
        List<BigDecimal> listNoteFinishBigDecimal = new ArrayList<>();
        for (Subject subject : listSubject){
            listNoteFinishBigDecimal.add(subject.getNote());
        }
        BigDecimal noteFinish = listNoteFinishBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return noteFinish;
    }

    private BigDecimal getQtdHours(List<Subject> listSubject){
        List<BigDecimal> listQtdHours = new ArrayList<>();
        for (Subject subject : listSubject){
            listQtdHours.add(subject.getQtdHours());
        }
        BigDecimal qtdHoursTotal = listQtdHours.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return qtdHoursTotal;
    }
}
