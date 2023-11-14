package com.ssafy.cozytrain.api.repository;

import com.ssafy.cozytrain.api.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
