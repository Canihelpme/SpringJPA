package First.Hellospring.controller;

public class MemberForm {
    private String name;
    //createMemberForm.html의 name과 matching됨.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
