package uap.usic.siga.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import uap.usic.siga.dto.UserDto;

/**
 * Created by Keno&Kemo on 30.09.2017..
 */

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String index (HttpSession session){
    	session.removeAttribute("dModal");
        return "website/index";
       // return "index";
    }
    
 
    @GetMapping(value = "/register")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "website/register";
    }
}
