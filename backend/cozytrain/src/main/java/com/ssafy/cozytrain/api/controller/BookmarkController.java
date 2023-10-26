package com.ssafy.cozytrain.api.controller;

import com.ssafy.cozytrain.api.dto.BookmarkDto;
import com.ssafy.cozytrain.api.service.BookmarkService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping
    @Operation(summary = "북마크 생성")
    public ApiUtils.ApiResult<Boolean> createBookmark(@RequestBody @Valid BookmarkDto bookmarkDto){
        return success(bookmarkService.saveBookmark(bookmarkDto));
    }

    @GetMapping
    @Operation(summary = "북마크 조회")
    public ApiUtils.ApiResult<List<BookmarkDto>> createBookmark(){
        return success(bookmarkService.getBookmark());
    }

}
