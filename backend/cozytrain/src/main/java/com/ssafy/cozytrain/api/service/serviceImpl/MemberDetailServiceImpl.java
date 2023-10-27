package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberLoginId(username).orElseThrow(() -> new NotFoundException("Not Found User"));
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setAccount(member);
        return userDetails;
    }
}
