package pl.block.bash.rest.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.block.bash.services.SomeDbService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {
    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    @Autowired
    private SomeDbService someMongoDbService2;


    @RequestMapping(value = "/base", method = RequestMethod.GET)
    @ResponseBody
    public List<String> base() {
        List<String> response = new ArrayList<>();

        response.add(someMongoDbService2.yell());

        return response;
    }

}