package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
