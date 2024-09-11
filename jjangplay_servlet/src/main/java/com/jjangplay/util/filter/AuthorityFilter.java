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

import com.jjangplay.member.vo.LoginVO;

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
		
		// 권한 처리를 위한 uri 정보를 받습니다.
		String uri = req.getRequestURI();
		System.out.println("AuthorityFilter.doFilter().uri = " + uri);
		
		session.setAttribute("uri", uri);
		
		// 페이지 권한을 integer로 가져옵니다. 데이터가 없으면 null이 나온다.
		// null이 나오면 누구나 접근 가능하다.
		Integer pageGradeNo = authMap.get(uri);
		LoginVO loginVo = null;
		
		if (pageGradeNo != null) {
			// 권한설정을 한 uri만 이곳으로 들어온다.
			
			loginVo = (LoginVO) session.getAttribute("login");
			// 로그인을 안했을 경우는 로그인 폼으로 이동
			if (loginVo == null) {
				// 권한 메시지 모달 창 표시
				session.setAttribute("msg", "로그인이 필요한 페이지 입니다. 로그인 해주세요");
				
				// 로그인 폼(페이지)으로 이동
				res.sendRedirect("/member/loginForm.do");
				return;
			}
			
			// user가 누구냐? (일반회원:1 or 관리자:9)
			Integer userGradeNo = loginVo.getGradeNo();
			
			if (pageGradeNo > 1) {
				System.out.println("<<<---관리자 권한 체크--->>>");
				if (pageGradeNo > userGradeNo) {
					System.out.println("===페이지 사용 권한 없음===");
					req.getRequestDispatcher("/WEB-INF/views/error/authority.jsp")
					.forward(req, res);
					
					return;
				}
			}
		} // 권한처리 끝
		
		
		
		// pass the request along the filter chain - 실제로 실행되는 곳으로 이동
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		// 서버가 시작될 때 한번만 실행이 된다.
		System.out.println("AuthorityFilter.init()--------------------");
		// 이미지게시판의 권한지정
		authMap.put("/image/writeForm.do", 1);
		authMap.put("/image/write.do", 1);
		authMap.put("/image/updateForm.do", 1);
		authMap.put("/image/update.do", 1);
		authMap.put("/image/delete.do", 1);
		
		// 회원관리의 권한지정
		authMap.put("/member/list.do", 9);
	}

}
















