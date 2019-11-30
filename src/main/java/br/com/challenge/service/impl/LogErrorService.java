package br.com.challenge.service.impl;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import br.com.challenge.repository.LogErrorRepository;
import br.com.challenge.service.interfaces.LogErrorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogErrorService implements LogErrorServiceInterface {

    @Autowired
    LogErrorRepository logErrorRepository;

    @Override
    public List<LogError> getLogErrors() {

        List<LogError> logErrorList = null;

        try{

            logErrorList = logErrorRepository.findAll();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return logErrorList;
    }

    @Override
    public LogError getLogError(Long id) {

        return logErrorRepository.findById(id).orElse(null);
    }

    @Override
    public LogError saveLogError(LogErrorDTO logErrorModel) {

        LogError logError = LogError.builder().details(logErrorModel.getDetails()).title(logErrorModel.getTitle()).build();

        return logErrorRepository.save(logError);
    }
}
