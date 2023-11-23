package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.TestQuerydslConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {TestQuerydslConfig.class})
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemListRepository itemListRepository;

    @Test
    void test(){
        var member = memberRepository.findByMemberId(1L);
        System.out.println(member.get().getMemberLoginId());
        var hi = itemListRepository.findByMember(member.orElse(null));
        for(var hihi : hi){
            System.out.println(hihi.getItem().getItemName());
        }
    }
}