package com.ssafy.cozytrain.api.service.serviceImpl;

import com.ssafy.cozytrain.api.dto.CollectionDto;
import com.ssafy.cozytrain.api.dto.ItemBoxDto;
import com.ssafy.cozytrain.api.dto.ItemDto;
import com.ssafy.cozytrain.api.entity.*;
import com.ssafy.cozytrain.api.repository.*;
import com.ssafy.cozytrain.api.service.CollectionService;
import com.ssafy.cozytrain.api.service.TrainService;
import com.ssafy.cozytrain.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    private final ItemRepository itemRepository;
    private final ItemListRepository itemListRepository;
    private final ItemBoxRepository itemBoxRepository;
    private final CountryRepository countryRepository;
    private final MemberRepository memberRepository;

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
                    .itemId(item.getItemId())
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

    @Transactional
    @Override
    public ItemDto.ItemDtoRes getRandomItem(Long countryId, Member member) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException("Not Found Country"));
        List<ItemBox> itemBoxes = itemBoxRepository.findItemBoxesByMemberAndCountry(member, country);
        if(itemBoxes.isEmpty())
            throw new NotFoundException("해당 뽑기권이 없습니다.");

        List<Item> items = itemRepository.findByCountry(country);

        Random random = new Random();
        int randomIndex = random.nextInt(items.size());
        Item item = items.get(randomIndex);

        Optional<ItemList> findItem = itemListRepository.findItemListByMemberAndItem(member, item);

        if(findItem.isEmpty()){
            itemListRepository.save(ItemList.builder()
                    .item(item)
                    .member(member)
                    .build());
        }

        // 뽑기권 하나 제거
        itemBoxRepository.delete(itemBoxes.get(0));

        return ItemDto.ItemDtoRes.builder()
                .item(item)
                .build();
    }

    @Transactional
    @Override
    public Boolean updateMemberCollection(Long itemId, Member member) {
        Item item = itemRepository.findById(itemId).orElseThrow(()-> new NotFoundException("Not Found Item"));
        ItemList itemList = itemListRepository.findItemListByMemberAndItem(member, item).orElseThrow(
                ()-> new NotFoundException("당신은 해당 아이템이 없습니다.")
        );
        member.updateMemberCollection(itemList);
        memberRepository.save(member);

        return true;
    }

    @Override
    public ItemDto.ItemDtoRes getMemberCollection(Member member) {
        ItemList itemList = member.getItem();
        if(itemList == null){
            throw new NotFoundException("대표 컬렉션이 없습니다.");
        }
        log.info(itemList.toString());

        return ItemDto.ItemDtoRes.builder()
                .item(itemList.getItem())
                .build();
    }
}
