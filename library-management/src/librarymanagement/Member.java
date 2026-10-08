package librarymanagement;

import java.util.UUID;

public class Member {
    private String memberID;
    private String name;
    private String email;
    private String phone;

    public Member(String name, String email, String phone) {
        this.memberID = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public String getMemberID() {
        return memberID;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
