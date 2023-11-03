package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    private Track track;

    @OneToOne(mappedBy = "station", cascade = CascadeType.ALL)
    private Country country;

    @Builder
    public Station(int dist, String continent, String region, Track track){
        this.dist = dist;
        this.continent = continent;
        this.region = region;
        this.track = track;
    }
}
