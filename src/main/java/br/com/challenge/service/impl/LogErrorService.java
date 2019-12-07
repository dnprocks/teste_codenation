package br.com.challenge.service.impl;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import br.com.challenge.entity.Users;
import br.com.challenge.repository.LogErrorRepository;
import br.com.challenge.repository.UsersRepository;
import br.com.challenge.service.interfaces.LogErrorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        return logErrorRepository.findById(id).orElse(null);
    }

    @Override
    public LogError saveLogError(LogErrorDTO logErrorModel, String requestIp) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users authenticatedUser = usersRepository.findByEmail(userDetails.getUsername());

        LogError logError = LogError.builder()
                                    .users(authenticatedUser)
                                    .level(logErrorModel.getErrorLevel())
                                    .requestIp(requestIp)
                                    .environment(logErrorModel.getEnvironment())
                                    .details(logErrorModel.getDetails())
                                    .title(logErrorModel.getTitle())
                                    .createdAt(LocalDateTime.now()).build();

        return logErrorRepository.save(logError);
    }
}
