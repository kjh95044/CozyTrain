package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.dto.CheckListDto;
import com.ssafy.cozytrain.api.dto.CheckListDto.*;
import com.ssafy.cozytrain.api.entity.CheckList;
import com.ssafy.cozytrain.api.entity.Member;

import java.util.List;
import java.util.Optional;

public interface CheckListService {
    Integer checkListSave(CheckListDtoReq checkListDtoReq);
    CheckListTodayRes checkListToday();
    Optional<CheckList> isCheckList(Member member);
}
