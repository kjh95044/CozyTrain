package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.CheckListDto;
import com.ssafy.cozytrain.api.dto.CheckListDto.*;
import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.CheckListItem;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.CheckListItemRepository;
import com.ssafy.cozytrain.api.repository.CheckListRepository;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckListServiceImpl implements CheckListService {
    private final CheckListRepository checkListRepository;
    private final CheckListItemRepository checkListItemRepository;
    private final MemberService memberService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer checkListSave(CheckListDtoReq checkListDtoReq) {
        // TODO: 로그인이 추가되면 member 로직 변경
        Member member = memberService.findByMemberId(1L);
        Optional<CheckList> isCheckList = isCheckList(member);

        CheckList todayCheckList = null;
        if(isCheckList.isPresent()) {
            todayCheckList = isCheckList.get();
        }
        else {
            CheckList checkList = CheckList.builder()
                    .checkListDate(LocalDate.now())
                    .updatedAt(LocalDateTime.now())
                    .member(member)
                    .build();
            todayCheckList = checkListRepository.save(checkList);
        }

        CheckListItem checkListItem = CheckListItem.builder()
                .elsId(checkListDtoReq.getElsId())
                .checkList(todayCheckList)
                .build();
        checkListItemRepository.save(checkListItem);
        return 1;
    }

    @Override
    public CheckListTodayRes checkListToday() {
        // TODO: 로그인이 추가되면 member 로직 변경
        Member member = memberService.findByMemberId(1L);
        Optional<CheckList> isCheckList = isCheckList(member);
        if(!isCheckList.isPresent()) return null;

        List<CheckListItem> checkList = checkListItemRepository.findAllByCheckList(isCheckList.get());
        CheckListTodayRes checkListTodayRes = CheckListTodayRes.builder()
                .checkListItem(checkList)
                .build();
        return checkListTodayRes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer checkListDelete(Long checkListItemId) {
        Integer res = checkListItemRepository.deleteByCheckListItemId(checkListItemId);
        List<CheckListRes> checkListDtoList = checkListToday().getCheckListDtoList();
        log.info(checkListDtoList.size()+"");
        if(checkListDtoList.isEmpty()) {
            log.info("dd");
            CheckList checkListItem = checkListRepository.findByCheckListDate(LocalDate.now());
            checkListRepository.deleteByCheckListId(checkListItem.getCheckListId());
        }
        return res;
    }

    @Override
    public Optional<CheckList> isCheckList(Member member) {
        return checkListRepository.findByMemberAndCheckListDate(member, LocalDate.now());
    }
}
