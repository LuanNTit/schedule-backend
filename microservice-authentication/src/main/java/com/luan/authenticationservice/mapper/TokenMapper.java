package com.luan.authenticationservice.mapper;

import com.luan.authenticationservice.dto.TokenDTO;
import com.luan.authenticationservice.model.TokenEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TokenMapper {
    List<TokenDTO> mapToTokenDTOs(List<TokenEntity> tokenEntities);
}
