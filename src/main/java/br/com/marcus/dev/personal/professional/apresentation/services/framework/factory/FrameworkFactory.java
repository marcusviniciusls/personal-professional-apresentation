package br.com.marcus.dev.personal.professional.apresentation.services.framework.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrameworkFactory {

    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CourseFactory courseFactory;

    public FrameworkResponse convertEntityInDto(Framework framework){
        FrameworkResponse frameworkResponse = new FrameworkResponse();
        frameworkResponse.setDescription(framework.getDescription());
        frameworkResponse.setName(framework.getName());
        if (centerEntityService.isStatusSuperEntity(framework.getCourse())){
            CourseResponse courseResponse = courseFactory.convertEntityInDto(framework.getCourse());
            frameworkResponse.setCourseResponse(courseResponse);
        }
        return frameworkResponse;
    }
}
