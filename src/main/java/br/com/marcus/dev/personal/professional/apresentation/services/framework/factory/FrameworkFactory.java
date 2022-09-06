package br.com.marcus.dev.personal.professional.apresentation.services.framework.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrameworkFactory {

    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CourseFactory courseFactory;
    @Autowired private ModelMapper modelMapper;

    public FrameworkResponse convertEntityInDto(Framework framework){
        FrameworkResponse frameworkResponse = new FrameworkResponse();
        frameworkResponse.setDescription(framework.getDescription());
        frameworkResponse.setName(framework.getName());
        frameworkResponse.setId(framework.getId());
        return frameworkResponse;
    }

    public Framework convertFormSaveInEntity(FrameworkSaveForm frameworkSaveForm){
        Framework framework = modelMapper.map(frameworkSaveForm, Framework.class);
        return framework;
    }

    public FrameworkResponse convertEntityInResponse(Framework framework){
        FrameworkResponse frameworkResponse = new FrameworkResponse();
        frameworkResponse.setName(framework.getName());
        frameworkResponse.setDescription(framework.getDescription());
        frameworkResponse.setId(framework.getId());
        return frameworkResponse;
    }

    public Framework convertUpdateFormInEntity(FrameworkUpdateForm frameworkUpdateForm, Framework framework){
        if (frameworkUpdateForm.getName() != null){
            framework.setName(frameworkUpdateForm.getName());
        }
        if (frameworkUpdateForm.getDescription() != null){
            framework.setDescription(frameworkUpdateForm.getDescription());
        }
        return framework;
    }
}
