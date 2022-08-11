package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteBranchActivityService {

    @Autowired private BranchActivityRepository branchActivityRepository;

    public void deleteBranchActivity(UUID id){
        try {
            branchActivityRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
            
        }

    }
}
