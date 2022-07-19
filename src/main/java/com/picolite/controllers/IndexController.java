package com.picolite.controllers;


import com.picolite.fakedb.FakeDB;
import com.picolite.models.ArticleContainer;
import com.picolite.tools.StringTools;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;


@Controller
public class IndexController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex()
    {
        ModelAndView index = new ModelAndView("index");
        return index;
    }
}
