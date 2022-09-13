package com.project.bootboard.config;


import com.project.bootboard.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class AdminSecurityConfig {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authenticationProvider(authenticationProvider2())
                // 요청 url 권한관련
                .antMatcher("/admin/**")
                .authorizeRequests(auth -> auth
                        .antMatchers("/admin/**")
                        .hasAuthority("ADMIN")) // 관리자 권한이 있는경우에만
                // 로그인 관련
                .formLogin(form -> form
                        .loginPage("/admin/login-form") // 로그인페이지 주소
                        .loginProcessingUrl("/admin/login") // 로그인 검증할 url
                        .defaultSuccessUrl("/admin/home") // 성공시 이동할 url
                        .failureUrl("/admin/login-form?error=fail") // 실패시 이동할 url
                        .permitAll())
                // 로그아웃 관련 옵션
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                        .logoutSuccessUrl("/admin/login-form")
                        .invalidateHttpSession(true))
                // 세션 관련 옵션
                .sessionManagement(session -> session
                        .maximumSessions(3) // 최대 허용 세션 수
                        .maxSessionsPreventsLogin(true) // 동시 로그인 차단기능 false가 차단
                        .expiredUrl("/admin/login-form") // 세션 끝날경우 이동 할 페이지3
                )
                // 권한이 없는데 접근할 경우 옵션
                .exceptionHandling().accessDeniedPage("/admin/notAuth")
                .and()
                .csrf().disable()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(adminService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}
