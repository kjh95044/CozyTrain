package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberLoginId(username).orElseThrow(() -> new NotFoundException("Not Found User"));
        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        return new User(member.getMemberLoginId(), member.getMemberPassword(), new ArrayList<>());
    }
}
