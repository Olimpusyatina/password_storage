package pro.olimpus.passstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pro.olimpus.passstore.domain.Role;
import pro.olimpus.passstore.domain.User;
import pro.olimpus.passstore.repos.UserRepo;

import java.util.Collections;

@Controller
public class RegistrationController {
    private UserRepo userRepo;
    @Autowired
    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb!= null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
