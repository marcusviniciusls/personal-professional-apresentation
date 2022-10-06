package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Profile;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.UserRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.net.URISyntaxException;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveTopicServiceTest {

    @Autowired private SaveTopicService saveTopicService;
    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private UserRepository userRepository;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit() throws URISyntaxException {
        // Study Plan
        StudyPlan studyPlan = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        studyPlan.setStatus(true);
        studyPlanRepository.save(studyPlan);

        User user = new User();
        user.setUuid(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff710"));
        user.setEmail("viniciusmls@outlook.com");
        user.setPassword("12345");
        user.addProfile(Profile.ADMIN);
        userRepository.save(user);
    }

    @Test
    public void saveTest(){
        Topic topic = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        topic.setName("Teste");
        StudyPlan studyPlan = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        studyPlan.setStatus(true);
        topic.setStudyPlan(studyPlan);
        //TopicResponse topicResponse = new TopicResponse("Teste", UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(topic);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        TopicSaveForm topicSaveForm = new TopicSaveForm("Teste", UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        TopicResponse topicResponse = saveTopicService.save(topicSaveForm);
        Assertions.assertThat(topicResponse.getName()).isEqualTo("Teste");
        Assertions.assertThat(topicResponse.getStudyPlanId()).isEqualTo(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(topicResponse.getName()).isNotNull();
        Assertions.assertThat(topicResponse.getStudyPlanId()).isNotNull();
    }
}
