package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.dto.ItemBoxDto;
import com.ssafy.cozytrain.api.dto.ItemDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.service.CollectionService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import com.ssafy.cozytrain.common.utils.JwtUtils;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ApiUtils.ApiResult<CollectionDto.CollectionDtoRes> getCollection(@RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(collectionService.getCollection(member));
    }

    @GetMapping("/item-boxes")
    @Operation(summary = "소유하고 있는 뽑기권 몇개인지 조회", description = "뽑기권에 해당하는 국가와 갯수를 알려준다.")
    public ApiUtils.ApiResult<List<ItemBoxDto.ItemBoxDtoRes>> getMemberItemBoxes(@RequestHeader("Authorization") String header) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(collectionService.getMemberItemBoxes(member));
    }

    @GetMapping("/random/{country_id}")
    @Operation(summary = "랜덤 뽑기", description = "어떤 아이템이 뽑혔는지 알려준다.")
    public ApiUtils.ApiResult<ItemDto.ItemDtoRes> getRandomItem(@RequestHeader("Authorization") String header, @PathVariable(name = "country_id") @ApiParam(value = "뽑기권 조회시 countryId") Long countryId) {
        String memberId = jwtUtils.getIdFromToken(header.substring(7));
        Member member = memberService.findByMemberLoginId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found User"));
        return success(collectionService.getRandomItem(countryId, member));
    }

}
