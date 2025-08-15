package project.jlog.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        // 기본 유효성 검사
        if(bindingResult.hasErrors()){
            return "join_form";
        }

        // 비밀번호 일치 확인
        if(!userRequestDTO.getPassword1().equals(userRequestDTO.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
        }

        // 중복검사: 여러 필드의 에러를 BindingResult에 한꺼번에 등록
        userService.validateDuplicate(userRequestDTO, bindingResult);

        if(bindingResult.hasErrors()){
            return "join_form";
        }

        // 중복없으면 회원가입 진행
        userService.userJoin(userRequestDTO.getUserId(), userRequestDTO.getPassword1(), userRequestDTO.getEmail());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "/login_form";
    }

    @GetMapping("/newpwd")
    public String getNewPwd(){
        return "/email/new_pwd";
    }
}
