package com.ssafy.cozytrain.api.service.serviceImpl;


import com.ssafy.cozytrain.api.dto.DreamDto;
import com.ssafy.cozytrain.api.entity.Dream;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.repository.DreamRepository;
import com.ssafy.cozytrain.api.service.DreamService;
import com.ssafy.cozytrain.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DreamServiceImpl implements DreamService {
    private final MemberService memberService;
    private final DreamRepository dreamRepository;

    @Override
    @Transactional(readOnly = true)
    public DreamDto.DreamDtoRes getDream(Long dreamId, Member member) {
        try {
            Dream dream = dreamRepository.findByDreamId(dreamId);

            return DreamDto.DreamDtoRes.builder()
                    .dreamId(dream.getDreamId())
                    .dreamType(dream.getDreamType())
                    .dreamContent(dream.getDreamContent())
                    .dreamDate(dream.getDreamDate())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DreamDto.DreamDtoListRes getDreams(Member member) {
        try {
            List<Dream> allByMember = dreamRepository.findAllByMember(member);

            return DreamDto.DreamDtoListRes.builder().dreamList(allByMember).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DreamDto.DreamDtoRes saveDream(DreamDto.DreamDtoReq dreamDtoReq, Member member) {
        try {
            Dream dream = Dream.builder()
                    .dreamContent(dreamDtoReq.getDreamContent())
                    .dreamType(dreamDtoReq.getDreamType())
                    .dreamDate(LocalDate.now())
                    .member(member).build();

            Dream saved = dreamRepository.save(dream);

            return DreamDto.DreamDtoRes.builder()
                    .dreamId(saved.getDreamId())
                    .dreamType(saved.getDreamType())
                    .dreamContent(saved.getDreamContent())
                    .dreamDate(saved.getDreamDate())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDream(Long dreamId, DreamDto.DreamDtoReq dreamDtoReq, Member member) {
        try {
            Dream dream = dreamRepository.findByDreamId(dreamId);
            dream.update(dreamDtoReq.getDreamContent(), dreamDtoReq.getDreamType());
            Dream saved = dreamRepository.save(dream);

            return saved != null;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDream(Long dreamId, Member member) {
        try {
            dreamRepository.deleteById(dreamId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
