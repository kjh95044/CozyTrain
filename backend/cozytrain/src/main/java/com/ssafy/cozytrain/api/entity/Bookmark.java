package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.entity.key.BookmarkKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @EmbeddedId
    private BookmarkKey bookmarkKey;

    @Override
    public String toString() {
        return "Bookmark{" +
                "bookmarkKey=" + bookmarkKey +
                '}';
    }
}
