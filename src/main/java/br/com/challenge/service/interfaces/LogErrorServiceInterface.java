package br.com.challenge.service.interfaces;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogErrorServiceInterface {

    LogError getLogError(Long id);

    List<LogError> getLogErrors();

    List<LogError> getLogErrors(int limit, int offset);

    LogError saveLogError(LogErrorDTO logError, String requestIp);

    String fileLogError(Long id);

    String deleteLogError(Long id);
}
