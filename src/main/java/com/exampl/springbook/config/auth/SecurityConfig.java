package com.exampl.springbook.config.auth;

import com.exampl.springbook.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().headers().frameOptions().disable() //h2-condole 화면을 사용하기위해 해당 옵션 disable (아마 기본 로그인화면 없애는기능?)
                .and().authorizeHttpRequests() //URL별 권한관리 설정 옵션의 시작점

                //권한 관리 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() //전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.GUEST.name()) //USER 권한만 열람
                .anyRequest().authenticated()// (anyRequest)-위에 설정된 값 이외의 나머지 URL을 나타냄, (authenticated)-인증된 사용자만 열람가능
                .and().logout().logoutSuccessUrl("/") //로그아웃 성공시 (/) 의 URL로 이동
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
                    // oauth2Client 의 로그인기능 설정, userInfoEndpoint 로그인 성공후 정보를 가져올 떄의 설정
    }
}