package br.com.marcus.dev.personal.professional.apresentation.services.course.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseFactory {

    public CourseResponse convertEntityInDto(Course course){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setLevelCourse(course.getLevelCourse().getNumber());
        courseResponse.setStatusCourse(course.getStatusCourse().getNumber());
        courseResponse.setDateFinishExpected(course.getDateFinishExpected());
        courseResponse.setDateFinishReal(course.getDateFinishReal());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setName(course.getName());
        courseResponse.setDateInitReal(course.getDateInitReal());
        courseResponse.setDateInitExpected(course.getDateInitExpected());
        return courseResponse;
    }
}
