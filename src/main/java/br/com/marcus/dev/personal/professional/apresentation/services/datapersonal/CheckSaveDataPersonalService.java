package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckSaveDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private CenterEntityService centerEntityService;

    public boolean verifyCheckSaveDataPersonal(){
        User user = centerEntityService.userLogged();
        List<DataPersonal> listDataPersonal =  dataPersonalRepository.checkHaveDataPersonal(user.getUuid());
        if (listDataPersonal.size() > 0){
            return false;
        } else if (listDataPersonal.size() == 0){
            return true;
        }
        return false;
    }
}
