package com.simplesdental.infra.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);

    public boolean existsByEmail(String email);

}
