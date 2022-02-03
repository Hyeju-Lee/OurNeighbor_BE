package solux.wansuki.OurNeighbor_BE.dto.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateDto {
    private String nickName;
    private String password;

    @Builder
    public MemberUpdateDto(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
}
