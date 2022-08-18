package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllEmailService {

    @Autowired private EmailRepository emailRepository;
    @Autowired private EmailFactory emailFactory;

    public Page<EmailDto> findAll(Pageable pageable){
        Page<Email> pageEmail = emailRepository.findAll(pageable);
        Page<EmailDto> pageEmailDto = pageEmail.map(x -> emailFactory.convertEntityInDto(x));
        return pageEmailDto;
    }
}
