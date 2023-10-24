package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.CheckListDto.*;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.CheckListRepository;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckListServiceImpl implements CheckListService {
    private final CheckListRepository checkListRepository;
    private final MemberService memberService;

    @Override
    public Long checkListSave(CheckListSaveReq checkListSaveReq) {
        Member member = memberService.findByMemberId(1L);
        log.info(member.toString());
//        checkListRepository.save()
        return null;
    }
}
