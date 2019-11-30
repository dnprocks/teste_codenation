package br.com.challenge.endpoints;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import br.com.challenge.service.impl.LogErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("logerror")
public class LogErrorResource {

    @Autowired
    LogErrorService logErrorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LogError> getLogErrors(){

        return logErrorService.getLogErrors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LogError getLogErrors(@PathVariable Long id) {

        return logErrorService.getLogError(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LogError saveUsers(@RequestBody LogErrorDTO logerror) {

        return logErrorService.saveLogError(logerror);
    }
}
