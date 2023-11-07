package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.entity.Item;
import com.ssafy.cozytrain.api.entity.ItemList;
import com.ssafy.cozytrain.api.entity.Member;
import com.ssafy.cozytrain.api.entity.Train;
import com.ssafy.cozytrain.api.repository.ItemListRepository;
import com.ssafy.cozytrain.api.repository.ItemRepository;
import com.ssafy.cozytrain.api.service.CollectionService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    private final ItemRepository itemRepository;
    private final ItemListRepository itemListRepository;

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
}
