package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.Country;
import com.ssafy.cozytrain.api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByCountry(Country country);
}
