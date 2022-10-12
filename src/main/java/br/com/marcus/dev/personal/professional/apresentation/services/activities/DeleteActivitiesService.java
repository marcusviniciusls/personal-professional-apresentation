package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteActivitiesService {

    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void deleteMovementCertificate(UUID idCertificate){
        Optional<Activities> optionalActivities = activitiesRepository.findByCertificate(idCertificate);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Certificate Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }

    public boolean deleteMovementHardSkills(UUID idHardSkills){
        Optional<Activities> optionalActivities = activitiesRepository.findByHardSkills(idHardSkills);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Hard Skills Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
        return true;
    }

    public void deleteMovementCourse(UUID idCourse){
        Optional<Activities> optionalActivities = activitiesRepository.findByCourse(idCourse);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Course Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }

    public void deleteMovementGraduation(UUID idGraduation){
        Optional<Activities> optionalActivities = activitiesRepository.findByGraduation(idGraduation);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Graduation Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }

    public void deleteMovementProfessionalExperience(UUID idProfessionalExperience){
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalExperience(idProfessionalExperience);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Professional Experience Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }

    public void deleteMovementProfessionalGoal(UUID idProfessionalGoal){
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalGoal(idProfessionalGoal);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Professional Goal Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }

    public void deleteMovementSoftSkills(UUID idSoftSkills){
        Optional<Activities> optionalActivities = activitiesRepository.findBySoftSkills(idSoftSkills);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Soft Skills Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }
}
