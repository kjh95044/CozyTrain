package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.get.MultiGetRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;
    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<ItemBox> itemBoxes;

    @OneToMany(mappedBy = "country")
    private List<Station> stations;

    @OneToMany(mappedBy = "country")
    private List<Item> items;
}
