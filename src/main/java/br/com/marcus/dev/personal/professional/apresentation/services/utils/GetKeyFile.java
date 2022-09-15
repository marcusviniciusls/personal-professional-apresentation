package br.com.marcus.dev.personal.professional.apresentation.services.utils;

import org.springframework.stereotype.Service;

@Service
public class GetKeyFile {

    public String getKeyFile(String fileFullName){
        int indexlastBarra = fileFullName.lastIndexOf("/") + 1;
        return fileFullName.substring(indexlastBarra);
    }
}
