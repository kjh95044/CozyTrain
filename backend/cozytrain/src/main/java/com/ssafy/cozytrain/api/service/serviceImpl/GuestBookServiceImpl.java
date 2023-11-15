package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.GuestBookDto;
import com.ssafy.cozytrain.api.entity.Country;
import com.ssafy.cozytrain.api.entity.GuestBook;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Station;
import com.ssafy.cozytrain.api.repository.CountryRepository;
import com.ssafy.cozytrain.api.repository.GuestBookRepository;
import com.ssafy.cozytrain.api.repository.StationRepository;
import com.ssafy.cozytrain.api.service.GuestBookService;
import com.ssafy.cozytrain.api.service.MemberService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NoContentException;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {
    private final GuestBookRepository guestBookRepository;
    private final CountryRepository countryRepository;
    @Override
    public Long createGuestBook(Member member, GuestBookDto.GuestBookReqDto guestBookReqDto) {

        Country country = countryRepository.findById(guestBookReqDto.getCountryId()).orElseThrow(() -> new NotFoundException("Not Found Station"));

        Optional<GuestBook> findGuestBook = guestBookRepository.findByMemberAndCountry(member,country);

        GuestBook guestBook;
        if(findGuestBook.isPresent()){
            guestBook = findGuestBook.get();
            guestBook.updateContent(guestBookReqDto.getGuestBookContent());
        }else {
            guestBook = GuestBook.builder()
                    .guestBookContent(guestBookReqDto.getGuestBookContent())
                    .country(country)
                    .member(member)
                    .createdAt(LocalDate.now())
                    .build();
        }
        return guestBookRepository.save(guestBook).getGuestBookId();
    }

    @Override
    public GuestBookDto.GuestBookResDto getRandomGuestBook(Member member, Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(()-> new NotFoundException("Not Found Country"));

        List<GuestBook> guestBooks = guestBookRepository.getGuestBookByMemberNotAndCountry(member,country);
        if(guestBooks.isEmpty()){
            throw new NoContentException("방명록이 없습니다.");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(guestBooks.size());

        return GuestBookDto.GuestBookResDto.builder()
                .guestBook(guestBooks.get(randomIndex))
                .build();
    }
}
