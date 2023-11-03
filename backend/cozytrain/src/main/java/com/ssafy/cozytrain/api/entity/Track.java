package com.ssafy.cozytrain.api.entity;

import com.ssafy.cozytrain.api.service.TrainService;
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
    private Long track_id;

    @OneToOne(mappedBy = "track")
    private Train train;

    @OneToMany(mappedBy = "track")
    private List<Station> stations;

}
