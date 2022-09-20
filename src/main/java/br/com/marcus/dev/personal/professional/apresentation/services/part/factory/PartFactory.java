package br.com.marcus.dev.personal.professional.apresentation.services.part.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListMaterial;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.language.FindByIdLanguageService;
import br.com.marcus.dev.personal.professional.apresentation.services.language.factory.LanguageFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PartFactory {

    @Autowired private LanguageFactory languageFactory;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private LanguageRepository languageRepository;

    public PartResponse convertEntityInResponse(Part part){
        PartResponse partResponse = new PartResponse();
        LanguageResponse languageResponse = languageFactory.convertEntityInResponse(part.getLanguage());

        partResponse.setName(part.getName());
        partResponse.setLevel(part.getLevel());
        partResponse.setLanguageResponse(languageResponse);

        return partResponse;
    }

    public Part convertSaveFormInEntity(PartSaveForm partSaveForm){
        Part part = new Part();
        Language language = languageRepository.findById(partSaveForm.getLanguageId()).get();

        if (partSaveForm.getListMaterial().size() > 0){
            for (ListMaterial listMaterial : partSaveForm.getListMaterial()){
                Optional<Material> optionalMaterial = materialRepository.findById(listMaterial.getId());
                if (optionalMaterial.isPresent()){
                    Material material = optionalMaterial.get();
                    part.addListMaterial(material);
                }
            }
        }
        part.setLanguage(language);
        part.setName(partSaveForm.getName());
        part.setLevel(Level.toEnum(partSaveForm.getLevel()));

        return part;
    }
}
