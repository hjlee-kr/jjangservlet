package com.jjangplay.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
//@WebFilter("/AuthorityFilter")
public class AuthorityFilter extends HttpFilter implements Filter {
       

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// uri에 따른 권한 저장 MAP
	// new HashMap<>에서 <>안에 아무표시가 없으면 선언한 타입을 그대로 가져옵니다.
	private static Map<String, Integer> authMap = new HashMap<>();

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("AuthorityFilter.doFilter()--------------------------");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 권한 처리를 위해서 로그인 정보를 session에서 가져옵니다.
		HttpSession session = req.getSession();

		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		// 권한 처리를 위한 uri 정보를 받습니다.
		String uri = req.getRequestURI();
		System.out.println("AuthorityFilter.doFilter().uri = " + uri);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
