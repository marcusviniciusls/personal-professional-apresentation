package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindAllListFrameworkService {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private CenterEntityService centerEntityService;

    public List<Framework> findAllFramework(List<ListFramework> listIdFramework){
        List<Framework> listFramework = new ArrayList<>();
        for (ListFramework list : listIdFramework){
            Optional<Framework> optionalFramework = frameworkRepository.findById(list.getId());
            if (optionalFramework.isPresent() || centerEntityService.isStatusSuperEntity(optionalFramework.get())){
                Framework framework = optionalFramework.get();
                listFramework.add(framework);
            }
        }
        return listFramework;
    }
}
