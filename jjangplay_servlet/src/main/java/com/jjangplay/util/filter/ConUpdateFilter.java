package com.jjangplay.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.main.controller.Init;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.util.exe.Execute;

/**
 * Servlet Filter implementation class ConUpdateFilter
 */
//@WebFilter("/ConUpdateFilter")
public class ConUpdateFilter extends HttpFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("ConUpdateFilter.doFilter()------------");
		
		// 최근접속일의 Controller 역할을 ConUpdateFilter.doFilter()가 한다.
		
		// 아이디
		HttpSession session = ((HttpServletRequest) request).getSession();
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		if (vo != null) {
			String id = vo.getId();
			// ConUpdateFilter -> Execute
			// -> ConUpdateService() -> MemberDAO.conUpdate()
			try {
				Execute.execute(Init.get("/member/conUpdate.do"), id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
