package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.entity.key.BookmarkKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @EmbeddedId
    private BookmarkKey bookmarkKey;
}
