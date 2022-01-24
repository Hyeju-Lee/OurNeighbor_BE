package solux.wansuki.OurNeighbor_BE.domain.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
