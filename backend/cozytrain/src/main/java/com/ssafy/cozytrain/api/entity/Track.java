package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.service.TrainService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long trackId;

    @OneToOne(mappedBy = "track")
    private Train train;

    @OneToMany(mappedBy = "track")
    private List<Station> stations;
}
