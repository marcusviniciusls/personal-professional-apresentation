package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.material.factory.MaterialFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class FindAllMaterialService {

    @Autowired private MaterialRepository materialRepository;
    @Autowired private MaterialFactory materialFactory;

    public Page<MaterialResponse> findAll(Pageable pageable){
        Page<Material> pageMaterial = materialRepository.findAll(pageable);
        Page<MaterialResponse> pageMaterialResponse = pageMaterial.map(x -> materialFactory.convertEntityInResponse(x));
        return pageMaterialResponse;
    }
}
