package com.project.bootboard.config;

import com.project.bootboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authenticationProvider(authenticationProvider())
                // 요청 url 권한관련
                .authorizeRequests(auth -> auth
                        .antMatchers("/resources/**").permitAll() // 리소스 허용
                        .antMatchers("/resource/**").permitAll() // 리소스 허용
                        .antMatchers("/sign/**").permitAll() // 인증을 안했어도 접근가능
                        .anyRequest().authenticated()) // 그외 모든 요청 인증 되어야 한다
                // 로그인 관련
                .formLogin(form -> form
                        .loginPage("/") // 로그인페이지 주소
                        .loginProcessingUrl("/member/login") // 로그인 검증할 url
                        .defaultSuccessUrl("/board/list") // 성공시 이동할 url
                        .failureUrl("/?error=fail") // 실패시 이동할 url
                        .permitAll())
                // 로그아웃 관련 옵션
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                // 세션 관련 옵션
                .sessionManagement(session -> session
                        .maximumSessions(3) // 최대 허용 세션 수
                        .maxSessionsPreventsLogin(true) // 동시 로그인 차단기능 false가 차단
                        .expiredUrl("/") // 세션 끝날경우 이동 할 페이지
                )
                // 권한이 없는데 접근할 경우 옵션
                .csrf().disable()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // 인증할때 어떤 객체랑 패스워드 엔코더 쓸껀지
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(memberService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}
