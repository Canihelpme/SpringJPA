package First.Hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!");
        return "hello"; //resources/templates/hello.html을 기본적으로 찾아서 감.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //localhost8080:~~?name=spring 이렇게 name에 requirement가 필요함 url 입력시.
        //default가 True라서.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http 통신에서 body부분에 직접 내용 넣겠다고 선언
    public String helloString(@RequestParam("name")String name) {
        return "hello " + name; // "hello spring"
    }
    //Template엔진과 다르게 그대로 문자를 보냄. 좀 비효율적이네.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        //private 객체는 외부에서 바로 사용 불가하니 getter, setter 사용하는건 당연.
        //Property 접근방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
