package com.ssafy.cozytrain.api.service.elastic;

import com.ssafy.cozytrain.api.entity.elastic.MediaInfoDocument;
import com.ssafy.cozytrain.api.repository.elastic.MediaInfoRepository;
import com.ssafy.cozytrain.api.service.elastic.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final MediaInfoRepository mediaInfoRepository;
    @Override
    public List<MediaInfoDocument> searchMedia(String mediaName) {
        List<MediaInfoDocument> mediaList = mediaInfoRepository.findByTitleNm(mediaName);
        return mediaList;
    }
}
