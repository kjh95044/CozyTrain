package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;
    private int dist;
    private String continent;
    private String region;

    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "train_id")
    private Train train;

    @OneToOne(mappedBy = "station", cascade = CascadeType.ALL)
    private Country country;

    @Builder
    public Station(int dist, String continent, String region, Train train){
        this.dist = dist;
        this.continent = continent;
        this.region = region;
        this.train = train;
    }
}
