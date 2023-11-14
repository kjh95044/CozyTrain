package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
