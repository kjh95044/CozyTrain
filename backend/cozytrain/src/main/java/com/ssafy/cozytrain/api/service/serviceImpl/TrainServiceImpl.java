package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Station;
import com.ssafy.cozytrain.api.entity.Track;
import com.ssafy.cozytrain.api.entity.Train;
import com.ssafy.cozytrain.api.repository.StationRepository;
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
    private final StationRepository stationRepository;

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

    @Override
    public TrainDto.TrainCurInfoDto getCurLocationInfo(Member member) {
        // TODO: 2023-11-03
        /*
            String region;
            int regionNum;
            int dist;
            int area;
         */
        Train train = trainRepository.findByMember(member).orElseThrow(
                ()-> {
                    log.error("Not Found Member Train");
                    return new NotFoundException("Not Found Member Train");
                    });
        log.info("유저의 기차 가져오기 : " + train.toString());

        Station station = train.getStation();

        if(station == null){
            throw new NotFoundException("Not Found Station, Station을 추가해주세요.");
        }else {
            // 나라 : train.getStation().getCountry().getCountryName();
            return TrainDto.TrainCurInfoDto.builder()
                    .region(station.getRegion())
                    .regionNum(station.getRegionNum())
                    .dist(train.getTrainCurDist())
                    .area(calculateArea(train.getTrainCurDist()))
                    .build();
        }
    }

    /*
    1번째 도시 → 2번째 도시 0구역 [0]
1번째 도시 → 2번째 도시 1구역 [1~60]
1번째 도시 → 2번째 도시 2구역 [61-120]
1번째 도시 → 2번째 도시 3구역 [121-180]
1번째 도시 → 2번째 도시 4구역 [181-240]
1번째 도시 → 2번째 도시 5구역 [241-299]
     */

    private static int calculateArea(int dist){
        int area = 0;

        if(dist >0 && dist <=60){
            area = 1;
        } else if (dist > 60 && dist <= 120) {
            area = 2;
        } else if (dist > 120 && dist <= 180) {
            area = 3;
        } else if (dist > 180 && dist <= 240) {
            area = 4;
        } else if (dist > 240 && dist < 300) {
            area = 5;
        }

        return area;
    }
}
