package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Country;
import com.ssafy.cozytrain.api.entity.GuestBook;
import com.ssafy.cozytrain.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuestBookRepository extends JpaRepository<GuestBook,Long> {
    List<GuestBook> getGuestBookByCountry(Country country);
    Optional<GuestBook> findByMemberAndCountry(Member member, Country country);
}
