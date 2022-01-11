package solux.wansuki.OurNeighbor_BE.dto.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberSaveDto {
    private String name;
    private String nickName;
    private String email;
    private String password;
    private String loginId;
    private List<String> roles;

    @Builder
    public MemberSaveDto(String name, String nickName, String email, String password, String loginId/*, List<String> roles*/) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.loginId = loginId;
        //this.roles = roles;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .nickName(nickName)
                .email(email)
                .password(password)
                .loginId(loginId)
                //.roles(roles)
                .build();
    }
}

