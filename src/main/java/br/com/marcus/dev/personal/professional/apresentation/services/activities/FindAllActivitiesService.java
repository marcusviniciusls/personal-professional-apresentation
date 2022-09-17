package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ActivitiesResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.factory.ActivitiesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllActivitiesService {

    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private ActivitiesFactory activitiesFactory;

    public List<ActivitiesResponse> findAll(){
        List<Activities> listActivities = activitiesRepository.findAll();
        List<ActivitiesResponse> listActivitiesResponse = new ArrayList<>();
        for (Activities activities : listActivities){
            ActivitiesResponse activitiesResponse = activitiesFactory.ConvertEntityInResponse(activities);
            listActivitiesResponse.add(activitiesResponse);
            if (listActivitiesResponse.size() == 10){
                break;
            }
        }
        return listActivitiesResponse;
    }
}
