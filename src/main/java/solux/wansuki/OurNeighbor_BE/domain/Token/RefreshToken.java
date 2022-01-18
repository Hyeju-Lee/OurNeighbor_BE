package solux.wansuki.OurNeighbor_BE.domain.Token;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String refreshTokenInfo;

    @Builder
    public RefreshToken(String refreshTokenInfo) {
        this.refreshTokenInfo = refreshTokenInfo;
    }
}
