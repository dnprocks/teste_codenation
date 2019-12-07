package br.com.challenge.service.impl;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import br.com.challenge.entity.Users;
import br.com.challenge.repository.LogErrorRepository;
import br.com.challenge.repository.UsersRepository;
import br.com.challenge.service.interfaces.LogErrorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogErrorService implements LogErrorServiceInterface {

    @Autowired
    LogErrorRepository logErrorRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<LogError> getLogErrors() {

        return logErrorRepository.findAll();
    }

    @Override
    public LogError getLogError(Long id) {

        LogError error = null;

        try{
            error = logErrorRepository.findById(id).orElse(null);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return error;
    }

    @Override
    public LogError saveLogError(LogErrorDTO logErrorModel) {

        Users user = usersRepository.findAll().get(0); //######## MODIFICAR PARA RECUPERAR O USUÁRIO LOGADO #########

        LogError logError = LogError.builder()
                                    .users(user)
                                    .level(logErrorModel.getErrorLevel())
                                    .requestIp("")          //######## MODIFICAR PARA RECUPERAR O IP DA REQUISIÇÃO ##########
                                    .environment(logErrorModel.getEnvironment())
                                    .details(logErrorModel.getDetails())
                                    .title(logErrorModel.getTitle())
                                    .createdAt(LocalDateTime.now()).build();

        return logErrorRepository.save(logError);
    }
}
