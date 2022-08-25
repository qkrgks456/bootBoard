package com.project.bootboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberDto implements UserDetails {
    private String memberNo;
    private String memberId;
    private String memberPw;
    private List<String> memberAuth;
    private String deleteYn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.memberAuth.forEach(s -> authorities.add(new SimpleGrantedAuthority(s)));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public String getPassword() {
        return this.memberPw;
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
        if (this.deleteYn.equals("Y")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MemberDto) {
            return this.memberId.equals(((MemberDto) obj).memberId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.memberId.hashCode();
    }
}
