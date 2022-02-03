package solux.wansuki.OurNeighbor_BE.dto.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String name;
    private String nickName;
    private String loginId;
    private String email;
    private String apartName;
    private String role;

    @Builder
    public MemberResponseDto (String name, String nickName, String loginId,
                              String email, String apartName, String role) {
        this.name = name;
        this.nickName = nickName;
        this.loginId = loginId;
        this.email = email;
        this.apartName = apartName;
        this.role = role;
    }
}
