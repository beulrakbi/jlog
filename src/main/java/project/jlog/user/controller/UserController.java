package project.jlog.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.jlog.user.dto.UserRequestDTO;
import project.jlog.user.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @GetMapping("/join")
    public String join(UserRequestDTO userRequestDTO){
        return "join_form";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequestDTO userRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "join_form";
        }
        if(!userRequestDTO.getPassword1().equals(userRequestDTO.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
            return "join_form";
        }
        userService.userJoin(userRequestDTO.getUserId(), userRequestDTO.getPassword1(), userRequestDTO.getEmail());
        return "redirect:/";
    }
}
