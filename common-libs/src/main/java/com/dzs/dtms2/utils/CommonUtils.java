package com.dzs.dtms2.utils;

import com.dzs.dtms2.domain.dto.AsyncResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        String[] ips = ip.split(",");
        return "0:0:0:0:0:0:0:1".equals(ips[0]) ? "127.0.0.1" : ips[0];
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    public static String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public static AsyncResponseDto createAsyncResponse(String requestId, String job) {
        return AsyncResponseDto.builder()
                .requestId(requestId)
                .job(job)
                .status(Constants.REQUEST_STATUS.IN_QUEUE)
                .build();
    }

}
