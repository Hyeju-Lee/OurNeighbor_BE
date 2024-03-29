package solux.wansuki.OurNeighbor_BE.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
    boolean existsByEmail(String email);
    Optional<Member> findByNickName(String nickName);
}
