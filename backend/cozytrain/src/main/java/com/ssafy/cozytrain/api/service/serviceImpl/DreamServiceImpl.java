package com.ssafy.cozytrain.api.service.serviceImpl;


import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.entity.Dream;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.DreamRepository;
import com.ssafy.cozytrain.api.service.DreamService;
import com.ssafy.cozytrain.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DreamServiceImpl implements DreamService {
    private final MemberService memberService;
    private final DreamRepository dreamRepository;

    @Override
    @Transactional(readOnly = true)
    public DreamDto.DreamDtoListRes getDreams() { // 리스트
        Member member = memberService.findByMemberId(1L);

        List<Dream> allByMember = dreamRepository.findAllByMember(member);
        // TODO:리스트를 DTO로
        return DreamDto.DreamDtoListRes.builder().dreamList(allByMember).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveDream(DreamDto.DreamDtoReq dreamDtoReq) {
        // TODO: 로그인 되면 바껴야 함
        Member member = memberService.findByMemberId(1L);
        
        // TODO: 멤버와 함께 저장
        Dream dream = Dream.builder()
                .dreamContent(dreamDtoReq.getDreamContent())
                .dreamType(dreamDtoReq.getDreamType())
                .dreamDate(LocalDate.now())
                .member(member).build();

        Dream saved = dreamRepository.save(dream);
        // TODO: 저장 됐는지 여부 확인
        if(saved == null) return false;

        return true;
    }


}
