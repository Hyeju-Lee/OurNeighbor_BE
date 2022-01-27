package solux.wansuki.OurNeighbor_BE.dto.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
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
    private String roles;
    private String apartName;
    private Apartment apartment;

    @Builder
    public MemberSaveDto(String name, String nickName, String email, String password,
                         String loginId, String roles, String apartName, Apartment apartment) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.loginId = loginId;
        this.roles = roles;
        this.apartName = apartName;
        this.apartment = apartment;
    }

}

