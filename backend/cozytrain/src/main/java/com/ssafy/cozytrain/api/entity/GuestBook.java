package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class GuestBook {
    @Id
    @Column(name = "guest_book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestBookId;
    private String guestBookContent;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Builder
    public GuestBook(String guestBookContent, Member member, Country country, LocalDate createdAt){
        this.guestBookContent = guestBookContent;
        this.member = member;
        this.country = country;
        this.createdAt = createdAt;
    }

    public void updateContent(String guestBookContent){
        this.guestBookContent = guestBookContent;
    }
}
