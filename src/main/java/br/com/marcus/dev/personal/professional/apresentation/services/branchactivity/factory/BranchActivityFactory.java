package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import org.springframework.stereotype.Component;

@Component
public class BranchActivityFactory {

    public BranchActivityResponse convertEntityInResponse(BranchActivity branchActivity){
        BranchActivityResponse response =  new BranchActivityResponse();
        response.setName(branchActivity.getName());
        return response;
    }

    public BranchActivity convertRequestInEntity(BranchActivityForm branchActivityForm){
        BranchActivity branchActivity = new BranchActivity();
        branchActivity.setName(branchActivityForm.getName());
        return branchActivity;
    }

    public BranchActivity convertEntityToUpdate(BranchActivityForm branchActivityForm, BranchActivity branchActivity){
        branchActivity.setName(branchActivity.getName());
        return branchActivity;
    }
}