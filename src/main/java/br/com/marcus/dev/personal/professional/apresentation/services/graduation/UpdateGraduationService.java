package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
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
public class UpdateGraduationService {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private FindByIdGraduationService findByIdGraduationService;
    @Autowired private GraduationFactory graduationFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private VerifyLocalDateService verifyLocalDateService;

    public GraduationResponse update(GraduationFormUpdate graduationFormUpdate, UUID idGraduation){
        LocalDate dateInitPreview =  graduationFormUpdate.getDateInitPreview();
        LocalDate dateFinishPreview = graduationFormUpdate.getDateFinishPreview();
        if (!verifyLocalDateService.dateInitBeforeDateFinish(dateInitPreview, dateFinishPreview)){
            throw new ErrorDateInitAfterDateFinish("DATE INIT AFTER DATE FINISH");
        }
        if (graduationFormUpdate.getDateInitReal() != null && graduationFormUpdate.getDateFinishReal() != null){
            LocalDate dateInitReal =  graduationFormUpdate.getDateInitReal();
            LocalDate dateFinishReal = graduationFormUpdate.getDateFinishReal();
            if (!verifyLocalDateService.dateInitBeforeDateFinish(dateInitReal, dateFinishReal)){
                throw new ErrorDateInitAfterDateFinish("DATE INIT AFTER DATE FINISH");
            }
        }
        Graduation graduation =  findByIdGraduationService.findByIdEntity(idGraduation);
        graduation = graduationFactory.convertFormUpdateInEntity(graduationFormUpdate, graduation);
        if (graduationFormUpdate.getDateInitReal() == null && graduationFormUpdate.getDateFinishReal() == null){
            graduation.setSituationGraduation(SituationGraduation.NOT_CONCLUSION);
        } else if (graduationFormUpdate.getDateInitReal() != null && graduationFormUpdate.getDateFinishReal() == null){
            graduation.setSituationGraduation(SituationGraduation.IN_PROGRESS);
        } else if (graduationFormUpdate.getDateInitReal() != null && graduationFormUpdate.getDateFinishReal() != null){
            graduation.setSituationGraduation(SituationGraduation.CONCLUSION);
        }
        graduation.setTypeGraduation(TypeGraduation.toEnum(graduationFormUpdate.getTypeGraduation()));
        graduation = (Graduation) centerEntityService.setDataToUpdate(graduation);
        graduation = graduationRepository.save(graduation);
        GraduationResponse graduationResponse = graduationFactory.convertEntityInDto(graduation);
        return graduationResponse;
    }
}
