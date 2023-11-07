package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.CollectionService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor

public class CollectionController {
    private final MemberService memberService;
    private final JwtUtils jwtUtils;
    private final CollectionService collectionService;

    @GetMapping
    @Operation(summary = "리워드 전체 조회", description = "리워드 전체 조회, 소유중인지 여부도 보여준다.")
    public ApiUtils.ApiResult<CollectionDto.CollectionDtoRes> getDream(@RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(collectionService.getCollection(member));
    }

    // TODO: 2023-11-07 뽑기권 몇개인지 확인하는 API
    // TODO: 2023-11-07 리워드 뽑기
}
