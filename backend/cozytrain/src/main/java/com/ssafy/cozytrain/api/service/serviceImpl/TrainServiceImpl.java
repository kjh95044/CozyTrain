package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.TrainDto;
import com.ssafy.cozytrain.api.dto.VisitDto;
import com.ssafy.cozytrain.api.entity.*;
import com.ssafy.cozytrain.api.repository.*;
import com.ssafy.cozytrain.api.service.ReportService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;
    private final TrackRepository trackRepository;
    private final StationRepository stationRepository;
    private final ItemBoxRepository itemBoxRepository;
    private final ReportRepository reportRepository;
    static final int DIST_BETWEEN_REGIONS = 300;

    @Transactional
    @Override
    public Long createTrain(Member member) {
        Optional<Track> trackFound = trackRepository.findById(1L);
        Track track;

        track = trackFound.orElseGet(() -> trackRepository.save(new Track()));
        Station station = stationRepository.findById(1L).orElseThrow(() -> new NotFoundException("Not Found Station"));

        Train train = Train.builder()
                .createdAt(LocalDateTime.now())
                .startDate(LocalDate.now())
                .member(member)
                .track(track)
                .station(station)
                .build();

        return trainRepository.save(train).getTrainId();
    }

    @Override
    public TrainDto.TrainCurInfoDto getCurLocationInfo(Member member) {

        Train train = getTrain(member);
        log.info("유저의 기차 정보 : " + train.toString());

        Station station = train.getStation();

        if (station == null) {
            throw new NotFoundException("Not Found Station, Station을 추가해주세요.");
        } else {
            // 나라 : train.getStation().getCountry().getCountryName();
            return TrainDto.TrainCurInfoDto.builder()
                    .region(station.getRegion())
                    .regionKor(station.getRegionKor())
                    .countryKor(station.getCountry().getCountryNameKor())
                    .regionNum(station.getRegionNum())
                    .dist(train.getTrainCurDist())
                    .area(calculateArea(train.getTrainCurDist()))
                    .build();
        }
    }

    @Transactional
    @Override
    public void moveTrain(int sleepScore, Member member, Report report) {
        Train train = getTrain(member);
        log.info("유저의 기차 가져오기 : " + train.toString());

        int dist = train.getTrainCurDist();
        int moveDist = sleepScoreToDist(sleepScore);

        dist += moveDist;

        // dist 가 300 이상이 되면 다음 지역으로 이동
        // 새로운 지역에 도착하면 해당 나라의 뽑기권 획득
        if (dist >= DIST_BETWEEN_REGIONS) {
            dist -= DIST_BETWEEN_REGIONS;
            moveStation(train);
            getReward(member, train.getStation().getCountry());
        }
        train.updateTrainCurDist(dist);
        report.updateMoveDist(moveDist);
        reportRepository.save(report);
        trainRepository.save(train);
    }

    private void getReward(Member member, Country country) {
        ItemBox itemBox = itemBoxRepository.save(ItemBox.builder()
                .country(country)
                .member(member)
                .build());
        log.info("Item Box 획득!!!!!!! "+ itemBox);
    }

    private void moveStation(Train train) {
        int stationsSize = train.getTrack().getStations().size();
        Long stationId = train.getStation().getStationId();
        log.info("기존 stationId는 : " + stationId);

        if (train.getStation().getStationId() == stationsSize) {
            stationId = 1L;
        } else {
            stationId += 1L;
        }
        log.info("이동후 stationId는 : " + stationId);

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> {
                    log.error("moveTrain 함수내에서 station을 찾지 못했습니다.");
                    return new NotFoundException("Not Found Station");
                });
        train.updateStation(station);
    }

    @Override
    public List<VisitDto.ContinentDto> checkTrainVisitContinent(Member member) {
        Train train = getTrain(member);

        String[] continents = {"asia", "europe", "africa", "oceania", "north_america", "south_america"};

        List<VisitDto.ContinentDto> continentDtoList = new ArrayList<>();
        boolean foundContinent = false;

        // 최근 방문 대륙이 나올때까지 순서대로 확인하고, 최근 대륙이 나오면 그 뒤로는 visit false
        for (int i = 0; i < 6; i++) {
            boolean visit = false;
            if (!foundContinent) {
                if (continents[i].equals(train.getStation().getContinent())) {
                    foundContinent = true;
                }
                visit = true;
            }

            continentDtoList.add(VisitDto.ContinentDto
                    .builder()
                    .continent(continents[i])
                    .visit(visit)
                    .build());
        }

        return continentDtoList;
    }

    @Override
    public List<VisitDto.CountryDto> checkTrainVisitCountry(String continent, Member member) {
        Train train = getTrain(member);
        List<VisitDto.CountryDto> countryDtoList = new ArrayList<>();
        Station userStation = train.getStation();

        boolean isFoundCountry = false;

        for (Station station : train.getTrack().getStations()) {
            boolean isAlreadyExists = false;
            boolean visit = false;

            // 해당 대륙 아니면 패스
            if (!station.getContinent().equals(continent)) continue;

            // 중복 체크
            for (VisitDto.CountryDto existingCountry : countryDtoList) {
                if (existingCountry.getCountry().equals(station.getCountry().getCountryName())) {
                    isAlreadyExists = true;
                    break;
                }
            }

            // 해당 나라 방문 할때까지는 visit true, 나머지는 false
            if (!isFoundCountry) {
                if (station.getCountry().getCountryId().equals
                        (userStation.getCountry().getCountryId())) {
                    isFoundCountry = true;
                }
                visit = true;
            }

            if (!isAlreadyExists) {
                countryDtoList.add(VisitDto.CountryDto.builder()
                        .country(station.getCountry().getCountryName())
                        .visit(visit)
                        .build());
            }

        }

        return countryDtoList;
    }

    @Override
    public Train getTrain(Member member) {
        return trainRepository.findByMember(member).orElseThrow(
                () -> {
                    log.error("Not Found Member Train");
                    return new NotFoundException("Not Found Member Train");
                });
    }

    /*
    100	300km
    90~99	점수 * 2.3 (95점이면 219km)
    80~89	점수 * 1.8 (85점이면 153km)
    70~79	점수 * 1.5 (75점이면 113km)
    60~69	점수 * 1.3 (65점이면 85km)
    50~59	점수 * 1.2 (55점이면 66km)
    40~49	점수 * 1.0 (45점이면 45km)
     */

    private static int sleepScoreToDist(int score) {
        double distDouble = 0.0;

        if (score >= 40 && score < 50) {
            distDouble = score * 1.0;
        } else if (score >= 50 && score < 60) {
            distDouble = score * 1.2;
        } else if (score >= 60 && score < 70) {
            distDouble = score * 1.3;
        } else if (score >= 70 && score < 80) {
            distDouble = score * 1.5;
        } else if (score >= 80 && score < 90) {
            distDouble = score * 1.8;
        } else if (score >= 90 && score < 100) {
            distDouble = score * 2.3;
        } else if (score == 100) {
            distDouble = 300;
        }

        return (int) Math.round(distDouble);
    }

    /*
        0구역 [0]
        1구역 [1~60]
        2구역 [61-120]
        3구역 [121-180]
        4구역 [181-240]
        5구역 [241-299]
     */
    private static int calculateArea(int dist) {
        int area = 0;

        if (dist > 0 && dist <= 60) {
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
