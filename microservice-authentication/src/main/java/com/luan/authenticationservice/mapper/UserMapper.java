package com.luan.authenticationservice.mapper;

import com.luan.authenticationservice.dto.UserDTO;
import com.luan.authenticationservice.model.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserEntity mapToUserEntity(UserDTO userDTO);
    UserDTO mapToUserDTO(UserEntity userEntity);
    List<UserDTO> mapToUserDTOs(List<UserEntity> userEntities);
    List<UserEntity> mapToUserEntities(List<UserDTO> userDTOs);
}
