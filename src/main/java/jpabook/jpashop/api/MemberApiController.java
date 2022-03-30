package jpabook.jpashop.api;

import jpabook.jpashop.Service.MemberService;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController //RestAPI style로 만들어주는 Annotation.
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원 조회
     */

    @GetMapping("api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    } //Entity 직접 노출

    @GetMapping("/api/v2/members")
    public Result memberV2() {
        List<Member> findMembers = memberService.findMembers();
        /**
        for (Member member : findMembers) {
            new MemberDto(member.getName());
        }
        **/
        List<MemberDto> collect = findMembers.stream().map(m -> new MemberDto(m.getName())).collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    } //List를 Obj type으로 한번 패킹.

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }


    /**
     * 회원 등록
     */
    @PostMapping("api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member); //join하면 바로 posting 됨 id 자동생성해주고ㅇㅇ
        return new CreateMemberResponse(id);
    }
    //@Requestbody = Convert Json -> Entity(Member)
    //@Valid = 넘어온 Json 정보를 다 검증.
    //Entity에 검증 매커니즘이 존재. 매우 큰 장애 유발 가능. DTO 사용하여 꼭 전송해줄 것.

    @PostMapping("api/v2/members")
    //@RequestBody로 CreateMemberReq 에 binding 함.
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    //Entity의 변경에 있어 자유로움. API 스펙 변경이 일어나지 않음.


    @Data //DTO
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }
    //API 스펙이 될 수 있음.
    //요구하는 Valid에 맞춰 대응 가능.

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }


    /**
     *  회원 수정
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse  updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id); //query
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());

    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }
}
