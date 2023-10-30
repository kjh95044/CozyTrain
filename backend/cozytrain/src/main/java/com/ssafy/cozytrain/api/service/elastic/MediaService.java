package com.ssafy.cozytrain.api.service.elastic;


import com.ssafy.cozytrain.api.entity.elastic.MediaInfoDocument;

import java.io.IOException;
import java.util.List;

public interface MediaService {
    List<MediaInfoDocument> searchMedia(String mediaName);

}
