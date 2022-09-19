package br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindByIdFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindByIdLanguageProgrammerService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class HardSkillsFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;
    @Autowired private FindByIdFrameworkService findByIdFrameworkService;
    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;

    public HardSkillsResponse convertEntityInResponse(HardSkills hardSkills){
        HardSkillsResponse hardSkillsResponse = modelMapper.map(hardSkills, HardSkillsResponse.class);
        for (Framework framework: hardSkills.getListFramework()){
            FrameworkResponse frameworkResponse = modelMapper.map(framework, FrameworkResponse.class);
            hardSkillsResponse.addListFrameworkResponse(frameworkResponse);
        }
        for (LanguageProgrammer languageProgrammer: hardSkills.getListLanguage()){
            LanguageProgrammerResponse languageProgrammerResponse = modelMapper.map(languageProgrammer, LanguageProgrammerResponse.class);
            hardSkillsResponse.addListLanguageProgrammerResponse(languageProgrammerResponse);
        }
        return hardSkillsResponse;
    }

    public HardSkills convertRequestSaveInEntity(HardSkillsSaveForm hardSkillsSaveForm){
        HardSkills hardSkills = new HardSkills();
        hardSkills.setName(hardSkillsSaveForm.getName());
        hardSkills.setDescription(hardSkillsSaveForm.getDescription());
        hardSkills.setLevel(Level.toEnum(hardSkillsSaveForm.getLevel()));
        if (hardSkillsSaveForm.getListIdFramework().size() > 0){
            for (ListFramework listFramework : hardSkillsSaveForm.getListIdFramework()){
                Optional<Framework> optionalFramework = findByIdFrameworkService.findByIdNotError(listFramework.getId());
                if (optionalFramework.isPresent()){
                    hardSkills.addListFramework(optionalFramework.get());
                }
            }
        }
        if (hardSkillsSaveForm.getListIdLanguageProgrammer().size() > 0){
            for (ListLanguageProgrammer listLanguageProgrammer : hardSkillsSaveForm.getListIdLanguageProgrammer()){
                Optional<LanguageProgrammer> optionalLanguageProgrammer = findByIdLanguageProgrammerService.findByIdNotError(listLanguageProgrammer.getId());
                if (optionalLanguageProgrammer.isPresent()){
                    hardSkills.addListLanguageProgrammer(optionalLanguageProgrammer.get());
                }
            }
        }
        return hardSkills;
    }

    public HardSkills convertUpdateFormInEntity(HardSkillsUpdateForm hardSkillsUpdateForm, HardSkills hardSkills){
        if (hardSkillsUpdateForm.getDescription() != null && !hardSkillsUpdateForm.getDescription().equals("")){
            hardSkills.setDescription(hardSkillsUpdateForm.getDescription());
        }
        if (hardSkillsUpdateForm.getName() != null && !hardSkillsUpdateForm.getName().equals("")){
            hardSkills.setName(hardSkillsUpdateForm.getName());
        }
        if (hardSkillsUpdateForm.getLevel() != null){
            hardSkills.setLevel(Level.toEnum(hardSkillsUpdateForm.getLevel()));
        }

        List<Framework> newListFramework = new ArrayList<>();
        if (hardSkillsUpdateForm.getListIdFramework().size() > 0){
            for (ListFramework listFramework : hardSkillsUpdateForm.getListIdFramework()){
                Optional<Framework> optionalFramework = findByIdFrameworkService.findByIdNotError(listFramework.getId());
                if (optionalFramework.isPresent()){
                    newListFramework.add(optionalFramework.get());
                }
            }
        }

        List<LanguageProgrammer> newLanguangeProgrammer = new ArrayList<>();
        if (hardSkillsUpdateForm.getListIdLanguageProgrammer().size() > 0){
            for (ListLanguageProgrammer listLanguageProgrammer : hardSkillsUpdateForm.getListIdLanguageProgrammer()){
                Optional<LanguageProgrammer> optionalLanguageProgrammer = findByIdLanguageProgrammerService.findByIdNotError(listLanguageProgrammer.getId());
                if (optionalLanguageProgrammer.isPresent()){
                    newLanguangeProgrammer.add(optionalLanguageProgrammer.get());
                }
            }
        }

        hardSkills.setListFramework(newListFramework);
        hardSkills.setListLanguage(newLanguangeProgrammer);

        return hardSkills;
    }
}
