package br.com.challenge.service.interfaces;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogErrorServiceInterface {

    List<LogError> getLogErrors();

    LogError getLogError(Long id);

    LogError saveLogError(LogErrorDTO logError);
}
