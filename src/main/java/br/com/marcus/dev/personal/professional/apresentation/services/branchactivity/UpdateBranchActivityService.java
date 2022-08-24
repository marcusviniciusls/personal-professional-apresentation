package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateBranchActivityService {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private BranchActivityFactory branchActivityFactory;
    @Autowired private CenterEntityService centerEntityService;

    public BranchActivityResponse update(BranchActivityForm branchActivityForm, UUID id){
        Optional<BranchActivity> optionalBranchActivity = branchActivityRepository.findById(id);
        if (optionalBranchActivity.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        BranchActivity branchActivity = optionalBranchActivity.get();
        if (!centerEntityService.isStatusSuperEntity(branchActivity)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        branchActivity = branchActivityFactory.convertEntityToUpdate(branchActivityForm, branchActivity);
        branchActivity = (BranchActivity) centerEntityService.setDataToUpdate(branchActivity);
        branchActivity = branchActivityRepository.save(branchActivity);
        return branchActivityFactory.convertEntityInResponse(branchActivity);
    }
}
