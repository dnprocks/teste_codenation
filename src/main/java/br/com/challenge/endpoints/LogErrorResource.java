package br.com.challenge.endpoints;

import br.com.challenge.dto.LogErrorDTO;
import br.com.challenge.entity.LogError;
import br.com.challenge.service.impl.LogErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("logerror")
public class LogErrorResource {

    @Autowired
    LogErrorService logErrorService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LogError getLogErrors(@PathVariable Long id) {

        return logErrorService.getLogError(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LogError> getLogErrors(){

        return logErrorService.getLogErrors();
    }

    @GetMapping(params = {"limit", "offset"})
    @ResponseStatus(HttpStatus.OK)
    public List<LogError> getLogErrorsWithLimitAndOffset(@RequestParam("limit") int limit, @RequestParam("offset") int offset){

        return logErrorService.getLogErrors(limit, offset);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LogError saveLogError(@RequestBody LogErrorDTO logError) {

        String requestIp = request.getRemoteAddr();

        return logErrorService.saveLogError(logError, requestIp);
    }

    @PutMapping("/file/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String fileLogError(@PathVariable Long id) {

        return logErrorService.fileLogError(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteLogError(@PathVariable Long id) {

        return logErrorService.deleteLogError(id);
    }
}
