package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.dto.ItemBoxDto;
import com.ssafy.cozytrain.api.entity.*;
import com.ssafy.cozytrain.api.repository.CountryRepository;
import com.ssafy.cozytrain.api.repository.ItemBoxRepository;
import com.ssafy.cozytrain.api.repository.ItemListRepository;
import com.ssafy.cozytrain.api.repository.ItemRepository;
import com.ssafy.cozytrain.api.service.CollectionService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    private final ItemRepository itemRepository;
    private final ItemListRepository itemListRepository;
    private final ItemBoxRepository itemBoxRepository;
    private final CountryRepository countryRepository;

    @Transactional
    @Override
    public CollectionDto.CollectionDtoRes getCollection(Member member) {
        var itemList = itemRepository.findAll();
        if (itemList.isEmpty())
            throw new NotFoundException("Not Found Items, Item 추가를 요청하세요.");

        List<ItemList> memberItemList = itemListRepository.findByMember(member);

        List<CollectionDto.CollectionItemDto> itemsDto = new ArrayList<>();

        for (Item item : itemList) {
            boolean isOwn = false;
            for (ItemList memberItem : memberItemList) {
                if (item.getItemId().equals(memberItem.getItem().getItemId())) {
                    isOwn = true;
                    break;
                }
            }

            itemsDto.add(CollectionDto.CollectionItemDto.builder()
                    .country(item.getCountry().getCountryName())
                    .isOwn(isOwn)
                    .itemDescription(item.getItemDescription())
                    .itemImgUrl(item.getItemImgUrl())
                    .itemName(item.getItemName())
                    .build());

        }

        return CollectionDto.CollectionDtoRes.builder()
                .items(itemsDto)
                .build();
    }

    @Override
    public List<ItemBoxDto.ItemBoxDtoRes> getMemberItemBoxes(Member member) {
        List<ItemBoxDto.ItemBoxDtoRes> itemBoxDtoResList = new ArrayList<>();

        List<ItemBox> itemBoxes = itemBoxRepository.findByMember(member);

        HashMap<Long, Integer> map = new HashMap<>();

        for (var itemBox : itemBoxes) {
            long countryId = itemBox.getCountry().getCountryId();
            map.put(countryId, map.getOrDefault(countryId, 0) + 1);
        }

        map.forEach(
                (id, cnt) -> itemBoxDtoResList.add(ItemBoxDto.ItemBoxDtoRes.builder()
                .country(countryRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Not Found Country, Country 추가를 요청하세요."))
                        .getCountryName())
                .countryId(id)
                .cnt(cnt)
                .build()));


        return itemBoxDtoResList;
    }
}
