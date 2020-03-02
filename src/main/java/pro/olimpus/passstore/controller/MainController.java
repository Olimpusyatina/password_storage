package pro.olimpus.passstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.olimpus.passstore.domain.Password;
import pro.olimpus.passstore.domain.User;
import pro.olimpus.passstore.repos.PasswordRepo;

@Controller
public class MainController {

    private PasswordRepo passwordRepo;
    @Autowired
    public void setPasswordRepo(PasswordRepo passwordRepo){this.passwordRepo = passwordRepo;}

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam (required = false, defaultValue = "") String resource, Model model){
        Iterable<Password> passwords;
        if (resource != null && resource.isEmpty()){
            passwords = passwordRepo.findAll();
        }else{
            passwords = passwordRepo.findByResource(resource);
        }
        model.addAttribute("resource", resource);
        model.addAttribute("passwords", passwords);
        return "main";
    }
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String resource, @RequestParam String login, @RequestParam String password, Model model ){
        Password pass = passwordRepo.getPasswordByResourceAndLogin(resource, login);
        if (pass != null){

        }else{
            pass = new Password(resource, login, password, user);
            passwordRepo.save(pass);
        }
        model.addAttribute("passwords", passwordRepo.findAll());
        return "main";
    }
}