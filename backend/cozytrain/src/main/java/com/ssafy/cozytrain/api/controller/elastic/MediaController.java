package com.ssafy.cozytrain.api.controller.elastic;

import com.ssafy.cozytrain.api.entity.elastic.MediaInfoDocument;
import com.ssafy.cozytrain.api.service.elastic.MediaService;
import com.ssafy.cozytrain.common.utils.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssafy.cozytrain.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
@Slf4j
public class MediaController {

    private final MediaService mediaService;

    // 검색(조회)
    @Operation(summary = "미디어 제목 검색", description = "드라마 속 장소나 아티스트가 방문한 장소의 정보를 검색어를 기반으로 반환합니다. ", tags = { "Media Controller" })
    @GetMapping("/search")
    public ApiUtils.ApiResult<List<MediaInfoDocument>> searchMedia(@RequestParam(name = "mediaName") String mediaName){
        List<MediaInfoDocument> mediaList = mediaService.searchMedia(mediaName);
        return success(mediaList);
    }
}
