package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.repository.ItemBoxRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.elasticsearch.monitor.os.OsStats;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class ItemBox {
    @Id
    @Column(name = "item_box_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemBoxId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Builder
    public ItemBox(Country country, Member member){
        this.country = country;
        this.member = member;
    }
}
