package com.ssafy.cozytrain.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ItemBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemBoxId;

    @OneToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;
}
