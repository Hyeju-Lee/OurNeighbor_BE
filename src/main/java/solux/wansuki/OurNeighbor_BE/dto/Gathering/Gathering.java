package solux.wansuki.OurNeighbor_BE.dto.Gathering;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;

@Getter
@NoArgsConstructor

public class Gathering extends BaseTimeEntity {
    @Entity

    public class Gathering {

        @Id
        @GenerationValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Cloumn (length = 500, nullable = false)
        private String content;
        private String title;
        private String category;

        @Builder
        public Gathering (String title, String content, String category) {
            this.title = title;
            this.content = content;
            this.category = category;
        }

        public void update(String title, String content) {
            this.title = title;
            this.content = content;
            this.category = category;
        }

        @Builder
        public Posts (String title, String content, String category) {
            this.title = title;
            this.content = content;
            this.category = category;
        }
    }
