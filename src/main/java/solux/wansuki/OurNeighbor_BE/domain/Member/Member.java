package solux.wansuki.OurNeighbor_BE.domain.Member;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Token.RefreshToken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickName;

    private String email;

    private String password;

    private String loginId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "refreshToken_id")
    private RefreshToken refreshToken;

    @ManyToOne
    @JoinColumn(name = "apart_id")
    private Apartment apartment;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getMember() != this)
            comment.setMember(this);
    }


    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
