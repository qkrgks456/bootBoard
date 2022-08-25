package com.project.bootboard.service;

import com.project.bootboard.dto.MemberDto;
import com.project.bootboard.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    public String idCheck(String id) {
        return memberMapper.idCheck(id);
    }

    @Transactional
    public int signMember(MemberDto memberDto) {
        int result = 0;
        String encodePw = bCryptPasswordEncoder.encode(memberDto.getMemberPw());
        memberDto.setMemberPw(encodePw);
        // 가입하기
        result += memberMapper.signMember(memberDto);
        // 권한넣기
        result += memberMapper.authInsert(memberDto);
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        MemberDto memberDto = memberMapper.getMember(memberId);
        if (memberDto == null) {
            throw new UsernameNotFoundException("User not authorized.");
        }
        // 권한 가져와서 넣기
        List<String> auth = memberMapper.getAuth(memberId);
        memberDto.setMemberAuth(auth);
        return memberDto;
    }
}
