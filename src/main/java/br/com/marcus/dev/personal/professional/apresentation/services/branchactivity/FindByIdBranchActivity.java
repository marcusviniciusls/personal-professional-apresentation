package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdBranchActivity {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private BranchActivityFactory branchActivityFactory;

    public BranchActivityResponse findById(UUID id){
        Optional<BranchActivity> branchActivityOptional = branchActivityRepository.findById(id);
        if (branchActivityOptional.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        BranchActivity branchActivity = branchActivityOptional.get();
        return branchActivityFactory.convertEntityInResponse(branchActivity);
    }
}
