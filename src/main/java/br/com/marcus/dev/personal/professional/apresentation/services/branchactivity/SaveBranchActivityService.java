package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaveBranchActivityService {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private BranchActivityFactory branchActivityFactory;
    @Autowired private CenterEntityService centerEntityService;

    public BranchActivityResponse save(BranchActivityForm branchActivityForm){
        BranchActivity branchActivity = branchActivityFactory.convertRequestInEntity(branchActivityForm);
        branchActivity = (BranchActivity) centerEntityService.setDataToSave(branchActivity);
        branchActivity = branchActivityRepository.save(branchActivity);
        return branchActivityFactory.convertEntityInResponse(branchActivity);
    }
}
