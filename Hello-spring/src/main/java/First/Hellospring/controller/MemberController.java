package First.Hellospring.controller;

import First.Hellospring.domain.Member;
import First.Hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Member controller가 Member service를 통해 회원가입 및 기능 제공.
//Controller annotation을 통해 MemberController라는 객체를 Spring이 구동될 때 동시에 생성하며 Spring이 들고 있음.

@Controller
public class MemberController {
    private final MemberService memberService;

    //memberController를 spring container가 뜰때 자동으로 호출되는데 이떄 Autowired annotation을 통해 memberService와 연결시켜줌.
    //Dependency injection
    //이렇게 연결시켜서 자동적으로 기능도 같이 끌어와서 사용 가능.
    //물론 MemberService에 @Service라는 annotation을 걸어줘야함. 순수한 자바 class라서 Spring이 MemberService 못알아봄.

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //조회시 보통 GetMapping.
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    //Mapping만 하는 역할 viewResolver 통해서 cMF.html Mapping해줌.

    //Data 전달시 PostMapping.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
        //회원가입 완료 후 Home 화면으로 화면 돌림.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }



}
