package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BranchActivityFactory {

    private ModelMapper modelMapper = new ModelMapper();

    public BranchActivityResponse convertEntityInResponse(BranchActivity branchActivity){
        return modelMapper.map(branchActivity, BranchActivityResponse.class);
    }
}
