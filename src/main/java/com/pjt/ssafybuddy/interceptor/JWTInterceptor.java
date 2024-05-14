package com.pjt.ssafybuddy.interceptor;

import com.pjt.ssafybuddy.service.member.MemberService;
import com.pjt.ssafybuddy.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@AllArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {
    private final String HEADER_AUTH = "Authorization";
    private JWTUtil jwtUtil;
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH);
        System.out.println(token);
        if(CorsUtils.isPreFlightRequest(request)){
            return true;
        }
        String atoken = null;
        if (token != null && token.startsWith("Bearer ")) {
            atoken = token.substring(7); // "Bearer " 이후의 문자열을 추출
            // 이후 로직
        }
        if(token != null && jwtUtil.checkToken(atoken)){
            String userId = jwtUtil.getUserId(atoken);
            String refreshToken = memberService.getRefreshToken(userId);
            if(token.equals(refreshToken)){
                log.info("토큰 사용 가능");
                String accessToken = jwtUtil.createAccessToken(userId);
                log.info("accessToken 재발급 : {}", accessToken);
                response.getWriter().write(accessToken);
                return true;
            }
            log.info("accessToken 사용 가능 : {}", atoken);
            return true;

        }else{
            log.info("토큰 사용 불가능");
            response.getWriter().write("accessToken_Invalid");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
