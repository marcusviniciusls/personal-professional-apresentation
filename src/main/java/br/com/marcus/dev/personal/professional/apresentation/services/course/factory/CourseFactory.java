package br.com.marcus.dev.personal.professional.apresentation.services.course.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseFactory {

    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;
    private FrameworkFactory frameworkFactory = new FrameworkFactory();

    public CourseResponse convertEntityInDto(Course course){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setLevelCourse(LevelCourse.toEnum(course.getLevelCourse().getNumber()));
        courseResponse.setStatusCourse(StatusCourse.toEnum(course.getStatusCourse().getNumber()));
        courseResponse.setDateFinishExpected(course.getDateFinishExpected());
        courseResponse.setDateFinishReal(course.getDateFinishReal());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setName(course.getName());
        courseResponse.setDateInitReal(course.getDateInitReal());
        courseResponse.setDateInitExpected(course.getDateInitExpected());
        for (Framework framework : course.getListFramework()){
            if (framework.isStatus()){
                FrameworkResponse frameworkResponse = frameworkFactory.convertEntityInResponse(framework);
                courseResponse.addListFrameworkResponse(frameworkResponse);
            }
        }
        for (LanguageProgrammer languageProgrammer : course.getListLanguage()){
            if (languageProgrammer.isStatus()){
                LanguageProgrammerResponse languageProgrammerResponse = languageProgrammerFactory.convertEntityInResponse(languageProgrammer);
                courseResponse.addListLanguageProgrammerResponse(languageProgrammerResponse);
            }
        }
        return courseResponse;
    }
}
