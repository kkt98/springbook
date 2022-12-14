package com.exampl.springbook.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //소셜로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 아닌지 확인 메소드

}
