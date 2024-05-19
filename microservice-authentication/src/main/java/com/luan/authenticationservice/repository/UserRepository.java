package com.luan.authenticationservice.repository;
import java.util.List;
import java.util.Optional;

import com.luan.authenticationservice.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByUserName(String userName);
	Optional<UserEntity> findByUserName(String userName);
	Optional<UserEntity> findByEmail(String email);
	List<UserEntity> findByUserNameContaining(String userName);
	Page<UserEntity> findAllBy(Pageable pageable);
}
