//생성시간, 수정시간 자동화

package solux.wansuki.OurNeighbor_BE.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreateDate;
import org.springframework.data.annotation.LastmodifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
