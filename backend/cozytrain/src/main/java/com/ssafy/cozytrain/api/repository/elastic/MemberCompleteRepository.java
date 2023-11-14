package com.ssafy.cozytrain.api.repository.elastic;

import com.ssafy.cozytrain.api.dto.FriendDto;
import com.ssafy.cozytrain.api.entity.elastic.MemberCompleteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCompleteRepository extends ElasticsearchRepository<MemberCompleteDocument, String> {
    List<MemberCompleteDocument> findByMemberLoginId(String friendLoginId);
}
