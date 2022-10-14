package br.com.marcus.dev.personal.professional.apresentation.services.subject.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormOnlySave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SubjectFactoryTest {

    @Autowired private SubjectFactory subjectFactory;

    @Test
    @Transactional
    @DisplayName("Converter SaveForm em Entidade (Subject)")
    public void convertSubjectFormSaveToEntityTest(){
        SubjectFormSave subjectFormSave = new SubjectFormSave("Programacao Web", BigDecimal.valueOf(100)
                , BigDecimal.valueOf(9.8), "description", "01/2022", 1, "imagem/record");
        // Executar método
        Graduation graduation = new Graduation();
        Subject subject = subjectFactory.convertSubjectFormSaveToEntity(subjectFormSave, graduation);

        // Testes Unitários
        Assertions.assertTrue(subject != null);
        Assertions.assertEquals("Programacao Web", subject.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), subject.getQtdHours());
        Assertions.assertEquals(BigDecimal.valueOf(9.8), subject.getNote());
        Assertions.assertEquals("description", subject.getDescription());
        Assertions.assertEquals("01/2022", subject.getPeriod());
        Assertions.assertEquals("imagem/record", subject.getImageReportRecord());
        Assertions.assertEquals(SituationSubject.NOT_APPROVED, subject.getSituationSubject());
    }

    @Test
    @Transactional
    @DisplayName("Converter SaveFormOnly em Entidade (Subject)")
    public void convertSubjectFormSaveOnlyToEntity(){
        SubjectFormOnlySave subjectFormOnlySave = new SubjectFormOnlySave("Programacao Web", BigDecimal.valueOf(100)
                , BigDecimal.valueOf(9.8), "description", "01/2022", 1);
        Subject subject = subjectFactory.convertSubjectFormSaveOnlyToEntity(subjectFormOnlySave);

        // Testes Unitários
        Assertions.assertTrue(subject != null);
        Assertions.assertEquals("Programacao Web", subject.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), subject.getQtdHours());
        Assertions.assertEquals(BigDecimal.valueOf(9.8), subject.getNote());
        Assertions.assertEquals("description", subject.getDescription());
        Assertions.assertEquals("01/2022", subject.getPeriod());
        Assertions.assertEquals(SituationSubject.NOT_APPROVED, subject.getSituationSubject());
    }

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Resposta (Subject)")
    public void convertEntityInResponseTest(){
        Subject subject = new Subject("Programacao Web", BigDecimal.valueOf(100)
                , BigDecimal.valueOf(9.8), "description", "01/2022", "" ,SituationSubject.APPROVED);
        SubjectResponse subjectResponse = subjectFactory.convertEntityInResponse(subject);

        // Testes Unitários
        Assertions.assertTrue(subjectResponse != null);
        Assertions.assertEquals("Programacao Web", subjectResponse.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), subjectResponse.getQtdHours());
        Assertions.assertEquals(BigDecimal.valueOf(9.8), subjectResponse.getNote());
        Assertions.assertEquals("description", subjectResponse.getDescription());
        Assertions.assertEquals("01/2022", subjectResponse.getPeriod());
        Assertions.assertEquals("", subjectResponse.getImageReportRecord());
        Assertions.assertEquals(SituationSubject.APPROVED, subjectResponse.getSituationSubject());
    }

    @Test
    @Transactional
    @DisplayName("Converter UpdateForm em Entidade (Subject)")
    public void convertUpdateFormInEntityTest(){
        SubjectFormUpdate subjectFormUpdate = new SubjectFormUpdate("Programacao Web", BigDecimal.valueOf(100)
                , BigDecimal.valueOf(9.8), "description", "01/2022", 1);
        Subject subject = new Subject("teste", BigDecimal.valueOf(0), BigDecimal.valueOf(0), "teste"
                , "teste", "teste" ,SituationSubject.APPROVED);
        subject = subjectFactory.convertUpdateFormInEntity(subjectFormUpdate, subject);

        // Testes Unitários
        Assertions.assertTrue(subject != null);
        Assertions.assertEquals("Programacao Web", subject.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), subject.getQtdHours());
        Assertions.assertEquals(BigDecimal.valueOf(9.8), subject.getNote());
        Assertions.assertEquals("description", subject.getDescription());
        Assertions.assertEquals("01/2022", subject.getPeriod());
        Assertions.assertEquals("teste", subject.getImageReportRecord());
        Assertions.assertEquals(SituationSubject.NOT_APPROVED, subject.getSituationSubject());
    }
}
