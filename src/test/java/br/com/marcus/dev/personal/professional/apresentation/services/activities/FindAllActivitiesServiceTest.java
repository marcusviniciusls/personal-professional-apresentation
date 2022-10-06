package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ActivitiesResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllActivitiesServiceTest {

    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private FindAllActivitiesService findAllActivitiesService;

    @Test
    @Transactional
    @DisplayName("Testar se o findAll está trazendo até 10 atividades")
    public void findAll10Test(){
        // Activities
        Activities activities1 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities2 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        Activities activities3 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Activities activities4 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));
        Activities activities5 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        Activities activities6 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Activities activities7 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff707"));
        Activities activities8 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"));
        Activities activities9 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff709"));
        Activities activities10 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff710"));
        Activities activities11 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff711"));
        Activities activities12 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        Activities activities13 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff713"));
        Activities activities14 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff714"));
        Activities activities15 = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715"));
        activitiesRepository.saveAll(Arrays.asList(activities1,activities2,activities3,activities4,activities5,
                activities6,activities7,activities8,activities9,activities10,activities11,activities12,activities13,
                activities14,activities15));
        List<ActivitiesResponse> listActivitiesResponse = findAllActivitiesService.findAll();
        Assertions.assertEquals(10, listActivitiesResponse.size());
    }
}
