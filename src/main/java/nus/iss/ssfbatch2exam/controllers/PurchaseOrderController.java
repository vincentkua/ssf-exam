package nus.iss.ssfbatch2exam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class PurchaseOrderController {

    @GetMapping(value = "/test")
    public String testController(Model model){

        model.addAttribute("test", "attribute is working...");
        return "test";
    }
    
}
