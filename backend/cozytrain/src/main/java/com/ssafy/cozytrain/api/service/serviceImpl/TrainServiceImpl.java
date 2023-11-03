package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Track;
import com.ssafy.cozytrain.api.entity.Train;
import com.ssafy.cozytrain.api.repository.TrackRepository;
import com.ssafy.cozytrain.api.repository.TrainRepository;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;
    private final TrackRepository trackRepository;

    @Override
    public Long createTrain(Member member) {
        Optional<Track> trackFound = trackRepository.findById(1L);
        Track track;

        track = trackFound.orElseGet(() -> trackRepository.save(new Track()));

        Train train = Train.builder()
                .createdAt(LocalDateTime.now())
                .startDate(LocalDate.now())
                .member(member)
                .track(track)
                .build();

        return trainRepository.save(train).getTrainId();
    }
}
