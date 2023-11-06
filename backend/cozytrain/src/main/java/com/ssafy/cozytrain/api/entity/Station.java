package com.ssafy.cozytrain.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long stationId;
    private int dist;
    private String continent;
    private String region;
    private int regionNum;

    @OneToMany(mappedBy = "station")
    private List<Train> trains;

    @OneToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    private Track track;

    @Builder
    public Station(int dist, String continent, String region, int regionNum, Track track){
        this.dist = dist;
        this.continent = continent;
        this.region = region;
        this.regionNum = regionNum;
        this.track = track;
    }
}
