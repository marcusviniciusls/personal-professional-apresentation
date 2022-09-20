package br.com.marcus.dev.personal.professional.apresentation.services.topic.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicFactory {

    @Autowired private ModelMapper modelMapper;

    public TopicResponse convertEntityInResponse(Topic topic){
        return modelMapper.map(topic, TopicResponse.class);
    }
}
