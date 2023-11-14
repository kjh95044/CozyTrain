package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.CheckListDto.*;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.CheckListService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/check-list")
@RequiredArgsConstructor
public class CheckListController {
    private final CheckListService checkListService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "체크리스트 생성")
    public ApiUtils.ApiResult<Integer> createCheckList(@RequestHeader("Authorization") String header, @RequestBody @Valid CheckListDtoReq checkListDtoReq){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(checkListService.checkListSave(checkListDtoReq, member));
    }

    @GetMapping
    @Operation(summary = "오늘 나의 체크리스트 목록 조회")
    public ApiUtils.ApiResult<CheckListTodayRes> getCheckList(@RequestHeader("Authorization") String header){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(checkListService.checkListToday(member));
    }

    @DeleteMapping("/{checkListId}")
    @Operation(summary = "나의 체크리스트 아이템 삭제")
    public ApiUtils.ApiResult<Integer> deleteCheckList(@RequestHeader("Authorization") String header, @PathVariable @ApiParam(value = "체크리스트 ID 값") Long checkListId){
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(checkListService.checkListDelete(checkListId, member));
    }

}
