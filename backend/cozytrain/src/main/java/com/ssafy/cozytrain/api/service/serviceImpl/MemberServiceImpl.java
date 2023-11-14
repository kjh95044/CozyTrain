package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.MemberDto.*;
import com.ssafy.cozytrain.api.dto.TokenDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.RefreshToken;
import com.ssafy.cozytrain.api.repository.MemberRepository;
import com.ssafy.cozytrain.api.repository.RefreshTokenRepository;
import com.ssafy.cozytrain.api.service.BookmarkService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import com.ssafy.cozytrain.common.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BookmarkService bookmarkService;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final S3Uploader s3Uploader;
    private final TrainService trainService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean signup(SignupReq signupReq) {
        if(memberRepository.findByMemberLoginId(signupReq.getMemberId()).isPresent()) {
            return false;
        }
        signupReq.update(passwordEncoder.encode(signupReq.getMemberPassword()));
        Member member = Member.builder().signupReq(signupReq).build();
        memberRepository.save(member);

        trainService.createTrain(member);
        return true;
    }

    @Override
    public LoginRes login(LoginReq loginReq, HttpServletResponse response) {
        Member member = memberRepository.findByMemberLoginId(loginReq.memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));

        if(!passwordEncoder.matches(loginReq.getMemberPassword(), member.getMemberPassword())) {
            throw new NotFoundException("Not Found User");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReq.getMemberId());
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findById(loginReq.getMemberId());

        String cookieValue = null;
        RefreshToken newToken = new RefreshToken(loginReq.memberId, tokenDto.getRefreshToken());
        if(refreshToken.isPresent()) {
            cookieValue = refreshToken.get().getRefreshToken();
        }else {
            cookieValue = tokenDto.getRefreshToken();
        }
        refreshTokenRepository.save(newToken);
        setHeader(response, tokenDto);

        String cookieName = "refreshToken";
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");

        return LoginRes.builder()
                .member(member)
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(cookieValue).build();
    }

    @Override
    public Optional<Member> findByMemberLoginId(String memberLoginId) {
        return memberRepository.findByMemberLoginId(memberLoginId);
    }

    @Override
    public Boolean findMemberLoginId(String memberLoginId) {
        if(findByMemberLoginId(memberLoginId).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateMemberRes updateMemberImg(MultipartFile file, Member member) throws IOException {
        if(member.getMemberImageUrl() != null) {
            s3Uploader.removeFile(member.getMemberImageName());
        }

        String imgName = file.getOriginalFilename();
        String imgPath = "profile/" + member.getMemberId();

        String url = s3Uploader.upload(file, imgPath, imgName);
        member.updateImg(url, imgName);

        memberRepository.save(member);
        return UpdateMemberRes.builder().memberImgUrl(url).build();
    }

    @Override
    public Boolean updateMemberName(UpdateMemberReq updateMemberReq, Member member) {
        member.updateMemberName(updateMemberReq.getMemberName());
        log.info(member.getMemberName());
        memberRepository.save(member);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMember(Member member) {
        memberRepository.deleteById(member.getMemberId());
        bookmarkService.deleteMemberBookmark(member);
        return true;
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtils.ACCESS_TOKEN, tokenDto.getAccessToken());
    }
}
