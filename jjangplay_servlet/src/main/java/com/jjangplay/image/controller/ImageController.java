package com.jjangplay.image.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.jjangplay.board.service.BoardDeleteService;
import com.jjangplay.board.service.BoardListService;
import com.jjangplay.board.service.BoardUpdateService;
import com.jjangplay.board.service.BoardViewService;
import com.jjangplay.board.service.BoardWriteService;
import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.image.vo.ImageVO;
import com.jjangplay.main.controller.Init;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.io.BoardPrint;
import com.jjangplay.util.io.In;
import com.jjangplay.util.page.PageObject;
import com.jjangplay.util.page.ReplyPageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// Image(이미지게시판) 의 메뉴를 선택하고, 데이터 수집(기능별), 예외처리
public class ImageController {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request) {
		System.out.println("ImageController.execute() ----------------");

	
			// 메뉴입력(uri)
			String uri = request.getRequestURI();
			
			// 데이터 수집을 위한 객체선언
			// 초기값 null 을 주어서 데이터를 받았는지 체크하고 처리한다.
			Object result = null;
			
			Long no = 0L;
			
			// 이동할 jsp 주소를 담아놀 변수
			String jsp = null;
			
			// 로그인 아이디 꺼내기
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("login"); 
			
			String id = null;
			if (loginVO != null) id = loginVO.getId();
			
			// 파일업로드 설정
			String savePath = "/upload/image";
			String realSavePath
				= request.getServletContext().getRealPath(savePath);
			// 용량제한
			int sizeLimit = 100 * 1024 * 1024;//100MB
			
			try {
				// 메뉴처리 CRUD
				switch (uri) {
				case "/image/list.do":
					System.out.println("1. 이미지게시판 리스트");
					//[ImageController] -> (Execute)
					//-> ImageListService -> ImageDAO.list()
					System.out.println("----주소창에 localhost/image/list.do 했을때 (Start)----");
					
					// 페이지 처리를 위한 객체, 넘어오는 페이지와 검색정보를 세팅
					PageObject pageObject = PageObject.getInstance(request);
					
					// 이미지게시판의 한페이지 이미지 갯수의 기본값을 6으로 변경
					String strPerPageNum = request.getParameter("perPageNum");
					if (strPerPageNum == null) pageObject.setPerPageNum(6);
					
					// Init.get(uri) : /image/list.do 키값을 가지고 service 주소를 가져온다.
					result = Execute.execute(Init.get(uri), pageObject);
					
					// DB처리후 pageObject값 확인
					System.out.println("ImageController.execute().pageObject = "
							+ pageObject);
					// 가져온 데이터를 request에 담는다.
					request.setAttribute("list", result);
					// pageObject를 request에 담는다.-> list.jsp에서 사용할 수 있도록
					request.setAttribute("pageObject", pageObject);
					
					System.out.println("----주소창에 localhost/image/list.do 했을때 (End)----");
					// "/WEB-INF/views/" + board/list +".jsp"
					jsp = "image/list";
					break;
				case "/image/view.do":
					System.out.println("2. 이미지 글보기");
					// 상세글보기
					//[ImageController] -> (Execute) ->
					// ImageViewService -> BoardDAO.view()
					no = Long.parseLong(request.getParameter("no"));
					
					// 전달데이터는 글번호만
					result = Execute.execute(Init.get(uri), no);
					
					// DB에서 가져온 데이터를 담는다.
					request.setAttribute("vo", result);
					
					// DispatcherServlet에서
					// "/WEB-INF/views/image/view.jsp" 경로를 만들어서
					// forword 한다.
					jsp="image/view";
					break;
				case "/image/writeForm.do":
					System.out.println("3. 이미지게시판 글쓰기 폼");
					jsp="image/writeForm";
					break;
				case "/image/write.do":
					System.out.println("3. 이미지게시판 글쓰기 처리");
					
					// 이미지 업로드 처리
					// new MultipartRequest(request, 실제저장위치, 사이즈제한,
					//	encoding, 중복처리객체-다른이름으로)
					// request를 multi에 담으면 request의 내용은 사라진다.
					MultipartRequest multi =
						new MultipartRequest(request, realSavePath, sizeLimit,
							"utf-8", new DefaultFileRenamePolicy());
					
					// 데이터 수집(사용자 -> 서버(request) -> Multi)
					String title = multi.getParameter("title");
					String content = multi.getParameter("content");
					String fileName = multi.getFilesystemName("imageFile");
					// id는 session에서 받아옴 -> 위에서 처리함(switch들어오기전)
						
					// 입력받은 데이터를 ImageVO 안에 저장(세팅) => DB에 넘겨주기위한
					ImageVO vo = new ImageVO();
					vo.setTitle(title);
					vo.setContent(content);
					// "/upload/image/파일이름" - 위치정보+파일이름
					vo.setFileName(savePath + "/" + fileName);
					vo.setId(id);
					
					//[ImageController] -> (Execute) ->
					// ImageWriteService -> ImageDAO.write()
					Execute.execute(Init.get(uri), vo);
					
					session.setAttribute("msg", "이미지글이 등록되었습니다.");
					// jsp 정보앞에 "redirect:" 가 붙어있으면 redirect로 처리
					// 없으면 forword
					jsp = "redirect:list.do?perPageNum="
						+multi.getParameter("perPageNum");
					break;
				case "/image/updateForm.do":
					System.out.println("4-1. 이미지 글수정 폼");
					//수정할 글번호 입력
					no = Long.parseLong(request.getParameter("no"));
					// ImageViewService()를 실행하기 위한 uri를 직접코딩한다.
					result = Execute.execute(Init.get("/image/view.do"), no);

					// 가져온 데이터를 updateForm.jsp로 보내기 위해 담는다.
					request.setAttribute("vo", result);
					// 이동한다.
					jsp = "image/updateForm";
					break;
				case "/image/update.do":
					System.out.println("4. 이미지게시판 글수정 처리");
					
					// updateForm 적은 데이터를 가져온다. (DB에 저장하기 위해)
					no = Long.parseLong(request.getParameter("no"));
					title = request.getParameter("title");
					content = request.getParameter("content");

					
					vo = new ImageVO();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setId(id); //session에 있는 id정보를 꺼내온다.

					// DB처리 ImageUpdateService()
					Execute.execute(Init.get(uri), vo);
					
					session.setAttribute("msg", "이미지 게시판 정보가 수정되었습니다.");
					
					// 페이지 정보 받고 & uri에 추가
					pageObject = PageObject.getInstance(request);
					
					jsp="redirect:view.do?no=" + no + "&"
							+ pageObject.getPageQuery();
					break;
				case "/image/delete.do":
					System.out.println("5. 이미지게시판 글삭제");
					// 데이터 수집 : 삭제할 글번호, 아이디
					vo = new ImageVO();
					
					vo.setNo(Long.parseLong(request.getParameter("no")));
					vo.setId(id);
					
					// DB처리
					result =Execute.execute(Init.get(uri), vo);
					
					// 결과표시
					if ((Integer) result == 1) {
						System.out.println();
						System.out.println("***********************");
						System.out.println("** " + vo.getNo() + "번 글이 삭제되었습니다.");
						System.out.println("***********************");
					}
					else {
						System.out.println();
						System.out.println("########################");
						System.out.println("## " + vo.getNo() + "번 글이 삭제되지 않았습니다.");
						System.out.println("########################");
					}
					
					// 이미지파일도 삭제한다.
					String deleteFileName = request.getParameter("deleteFileName");
					// 기존 이미지 파일을 지운다. (존재하면)
					File deleteFile = new File(request.getServletContext()
							.getRealPath(deleteFileName));
					if (deleteFile.exists()) deleteFile.delete();
					
					session.setAttribute("msg", "이미지 게시판 글삭제가 완료되었습니다.");
					
					jsp = "redirect:list.do?perPageNum="
						+ request.getParameter("perPageNum");
					break;
				case "/image/imageChange.do":
					System.out.println("6. 이미지 바꾸기 처리");
					// 이미지 업로드 처리
					// new MultipartRequest(request, 실제저장위치, 사이즈제한,
					//	encoding, 중복처리객체-다른이름으로)
					// request를 multi에 담으면 request의 내용은 사라진다.
					multi =
						new MultipartRequest(request, realSavePath, sizeLimit,
							"utf-8", new DefaultFileRenamePolicy());
					
					// 데이터 수집(사용자:form->서버->request->multi)
					// 글번호, 새로운 이미지 경로+파일이름
					no = Long.parseLong(multi.getParameter("no"));
					fileName = multi.getFilesystemName("imageFile");
					
					deleteFileName = multi.getParameter("deleteFileName");
					
					// 변수 vo에 no와 fileName을 담는다.
					vo = new ImageVO();
					vo.setNo(no);
					vo.setFileName(savePath + "/" +fileName);
					
					// 서비스로 간다.
					// ImageControll -> Execute
					// -> ImageChangeService() -> ImageDAO.imageChange()
					Execute.execute(Init.get(uri), vo);
					
					// 기존 이미지 파일을 지운다. (존재하면)
					deleteFile = new File(request.getServletContext()
							.getRealPath(deleteFileName));
					if (deleteFile.exists()) deleteFile.delete();
					
					session.setAttribute("msg", "이미지 바꾸기가 성공했습니다.");
					
					// 페이지 정보 받기 & uri에 붙이기
					pageObject = PageObject.getInstance(request);
					jsp = "redirect:view.do?no=" + no
						+ "&" + pageObject.getPageQuery();
					break;
				default:
					request.setAttribute("uri", uri);
					jsp = "error/404";
				} // end of switch
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("e", e);
				jsp = "error/500";
			}
		
			return jsp;
	} // end of execute
} // end of class
