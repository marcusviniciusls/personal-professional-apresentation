package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteBranchActivityService {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void deleteBranchActivity(UUID id){
        try {
            branchActivityRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
            Optional<BranchActivity> optionalBranchActivity = branchActivityRepository.findById(id);
            BranchActivity branchActivityUpdate = (BranchActivity) centerEntityService.setDataToDelete(optionalBranchActivity.get());
            branchActivityRepository.save(branchActivityUpdate);
        }
    }
}