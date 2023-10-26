package com.ssafy.cozytrain.api.entity.key;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class BookmarkKey implements Serializable {
    private Long elsId;
    private Long memberId;

    @Builder
    public BookmarkKey(Long elsId, Long memberId) {
        this.elsId = elsId;
        this.memberId = memberId;
    }
}
