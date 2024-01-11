package mouad.louhibi.api.controller.api;

import mouad.louhibi.api.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private AppService service;
}
