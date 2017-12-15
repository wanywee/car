package com.carTravelsky.listener.system.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 空格过滤
 * @ClassName: SessionFilter
 * @Description: TODO
 * @author: dg
 * @date: 2017年10月23日 上午10:32:53
 */
public class SessionFilter extends OncePerRequestFilter {  
	  
    @Override  
    protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);  
            filterChain.doFilter(requestWrapper, response);  
    }  
} 
