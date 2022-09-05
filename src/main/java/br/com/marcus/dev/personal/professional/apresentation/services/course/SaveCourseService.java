package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindAllListFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindAllListLanguageProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveCourseService {

    @Autowired private CourseFactory courseFactory;
    @Autowired private FindAllListFrameworkService findAllListFrameworkService;
    @Autowired private FindAllListLanguageProgrammerService findAllListLanguageProgrammerService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CourseRepository courseRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    public CourseResponse save(CourseSaveForm courseSaveForm){
        Course course = courseFactory.convertFormSaveInEntity(courseSaveForm);
        List<Framework> listFramework = findAllListFrameworkService.findAllFramework(courseSaveForm.getListFrameworkId());
        List<LanguageProgrammer> listLanguageProgrammer = findAllListLanguageProgrammerService.findAllLanguageProgrammer(courseSaveForm.getListLanguageProgrammerId());
        course.setListFramework(listFramework);
        course.setListLanguage(listLanguageProgrammer);
        course = (Course) centerEntityService.setDataToSave(course);
        course = courseRepository.save(course);
        for (Framework framework : listFramework){
            framework = (Framework) centerEntityService.setDataToSave(framework);
            framework.setCourse(course);
            frameworkRepository.save(framework);
        }
        for (LanguageProgrammer languageProgrammer : listLanguageProgrammer){
            languageProgrammer = (LanguageProgrammer) centerEntityService.setDataToSave(languageProgrammer);
            languageProgrammer.setCourse(course);
            languageProgrammerRepository.save(languageProgrammer);
        }
        CourseResponse courseResponse = courseFactory.convertEntityInDto(course);
        return courseResponse;
    }
}
