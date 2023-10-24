package com.ssafy.cozytrain.api.service;

import com.ssafy.cozytrain.api.entity.Member;

public interface MemberService {
    Member findByMemberId(Long memberId);
}
