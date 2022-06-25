package com.example.foodlist.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {
    //타임리프에서 특정 html 뷰에 보낼때 사용
    @GetMapping("/main")
    public ModelAndView main(){
        //템플릿 하위 경로이다/. View설정 경로(만약 template디렉토리 안에
        //다른 디렉토리 생성시 (파일이름/index)-맨 앞에 /없음 주의
        return new ModelAndView("index");
    }
}
