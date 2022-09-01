package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorDateInitAfterDateFinish;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.VerifyLocalDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class SaveGraduationService {

    @Autowired private GraduationFactory graduationFactory;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private VerifyLocalDateService verifyLocalDateService;

    public UUID save(GraduationFormSave graduationFormSave){
        LocalDate dateInitPreview =  graduationFormSave.getDateInitPreview();
        LocalDate dateFinishPreview = graduationFormSave.getDateFinishPreview();
        if (!verifyLocalDateService.dateInitBeforeDateFinish(dateInitPreview, dateFinishPreview)){
            throw new ErrorDateInitAfterDateFinish("DATE INIT AFTER DATE FINISH");
        }
        if (graduationFormSave.getDateInitReal() != null && graduationFormSave.getDateFinishReal() != null){
            LocalDate dateInitReal =  graduationFormSave.getDateInitReal();
            LocalDate dateFinishReal = graduationFormSave.getDateFinishReal();
            if (!verifyLocalDateService.dateInitBeforeDateFinish(dateInitReal, dateFinishReal)){
                throw new ErrorDateInitAfterDateFinish("DATE INIT AFTER DATE FINISH");
            }
        }
        Graduation graduation = graduationFactory.convertFormSaveToEntity(graduationFormSave);
        return graduation.getId();
    }
}
