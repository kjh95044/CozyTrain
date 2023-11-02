package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.dto.DreamDto.DreamDtoReq;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.DreamService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
    private final DreamService dreamService;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @GetMapping("/{dreamId}")
    @Operation(summary = "꿈 조회", description = "dreamType: { 0: 돈/재물, 1: 음식, 2: 지인, 3: 똥, 4: 악몽, 5: 기타 }")
    public ApiUtils.ApiResult<DreamDto.DreamDtoRes> getDream(@RequestHeader("Authorization") String header, @PathVariable Long dreamId) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(dreamService.getDream(dreamId, member));
    }

    @GetMapping
    @Operation(summary = "꿈 전체 조회", description = "dreamType: { 0: 돈/재물, 1: 음식, 2: 지인, 3: 똥, 4: 악몽, 5: 기타 }")
    public ApiUtils.ApiResult<DreamDto.DreamDtoListRes> getDreams(@RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(dreamService.getDreams(member));
    }

    @PostMapping
    @Operation(summary = "꿈 등록", description = "dreamType: { 0: 돈/재물, 1: 음식, 2: 지인, 3: 똥, 4: 악몽, 5: 기타 }")
    public ApiUtils.ApiResult<Boolean> createDream(@RequestHeader("Authorization") String header, @RequestBody @Valid DreamDtoReq dreamDtoReq) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(dreamService.saveDream(dreamDtoReq, member));
    }

    @PatchMapping("/{dreamId}")
    @Operation(summary = "꿈 수정", description = "dreamType: { 0: 돈/재물, 1: 음식, 2: 지인, 3: 똥, 4: 악몽, 5: 기타 }")
    public ApiUtils.ApiResult<Boolean> updateDream(@RequestHeader("Authorization") String header, @PathVariable Long dreamId, @RequestBody @Valid DreamDtoReq dreamDtoReq) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(dreamService.updateDream(dreamId, dreamDtoReq, member));
    }

    @DeleteMapping("/{dreamId}")
    @Operation(summary = "꿈 삭제", description = "dreamType: { 0: 돈/재물, 1: 음식, 2: 지인, 3: 똥, 4: 악몽, 5: 기타 }")
    public ApiUtils.ApiResult<Boolean> deleteDream(@RequestHeader("Authorization") String header, @PathVariable Long dreamId) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(dreamService.deleteDream(dreamId, member));
    }
}
