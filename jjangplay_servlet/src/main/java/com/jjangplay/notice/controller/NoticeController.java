package com.jjangplay.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.jjangplay.main.controller.Init;
import com.jjangplay.notice.service.NoticeDeleteService;
import com.jjangplay.notice.service.NoticeListService;
import com.jjangplay.notice.service.NoticeUpdateService;
import com.jjangplay.notice.service.NoticeViewService;
import com.jjangplay.notice.service.NoticeWriteService;
import com.jjangplay.notice.vo.NoticeVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.io.In;
import com.jjangplay.util.io.NoticePrint;
import com.jjangplay.util.page.PageObject;

public class NoticeController {

	public String execute(HttpServletRequest request) {

			
			// uri
			String uri = request.getRequestURI();
			// 이동할 주소
			String jsp = null;
			
			// 데이터 수집을 위한 변수
			Object result = null;
			
			// 입력 받는 데이터 (글번호를 위한)
			Long no = 0L;
		
			
			try {
				switch (uri) {
				case "/notice/list.do":
					// NoticeController -> execute -> NoticeListService
					// -> NoticeDAO.list()
					System.out.println("1. 공지사항 리스트");
					// 페이지 처리를 위한 객체, 넘어오는 페이지와 검색정보를 세팅
					PageObject pageObject = PageObject.getInstance(request);
					// DB에서 데이터 가져오기
					result = Execute.execute(Init.get(uri), pageObject);
					// 가져온 데이터 담기
					request.setAttribute("list", result);
					request.setAttribute("pageObject", pageObject);
					jsp = "notice/list";
					break;
				case "/notice/view.do":
					System.out.println("2. 공지사항 글보기");
					// NoticeController -> execute -> NoticeViewService
					// -> NoticeDAO.view()
					// 공지사항은 조회수가 없으므로 increase()메서드가 없다.
					no = Long.parseLong(request.getParameter("no"));
					// DB에서 데이터를 가져오기
					result = Execute.execute(Init.get(uri), no);
					// 가져온 데이터를 담는다.(저장한다)
					request.setAttribute("vo", result);
					// 데이터를 화면에 보여준다.
					jsp = "notice/view"; //jsp 파일 + 경로
					break;
				case "/notice/writeForm.do":
					System.out.println("3. 공지사항 글쓰기 폼");
					jsp = "notice/writeForm";
					break;
				case "/notice/write.do":
					System.out.println("3. 공지사항 글쓰기 처리");
					// 데이터 수집
					// 제목, 내용, 게시시작일, 게시종료일
					String title = In.getStr("제목");
					String content = In.getStr("내용");
					String startDate = In.getStr("시작일(YYYY-MM-DD)");
					String endDate = In.getStr("종료일(YYYY-MM-DD)");
					
					NoticeVO writeVO = new NoticeVO();
					writeVO.setTitle(title);
					writeVO.setContent(content);
					writeVO.setStartDate(startDate);
					writeVO.setEndDate(endDate);
					
					// NoticeController -> execute -> NoticeWriteService
					// -> NoticeDAO.write()
					Execute.execute(new NoticeWriteService(), writeVO);
					
					break;
				case "4":
					System.out.println("4. 공지사항 글수정");
					// 수정 글번호
					no = In.getLong("수정할 글번호");
					
					// 수정할 데이터 가져오기 (view 서비스를 이용해서)
					NoticeVO updateVO = (NoticeVO)Execute.execute(
							new NoticeViewService(), no);
					
					// while문
					whileloop:// 빠져나오기 위한 라벨
					while(true) {
						new NoticePrint().print(updateVO);
						System.out.println();
						System.out.println("-----------------------------------");
						System.out.println("-- 1.제목, 2.내용, 3. 게시시작일      --");
						System.out.println("-- 4.게시종료일, 9.수정취소, 0.수정완료 --");
						System.out.println("-----------------------------------");
						String item = In.getStr("수정 항목 선택");
						switch(item) {
						case "1":
							updateVO.setTitle(In.getStr("제목"));
							break;
						case "2":
							updateVO.setContent(In.getStr("내용"));
							break;
						case "3":
							updateVO.setStartDate(In.getStr("시작일(YYYY-MM-DD)"));
							break;
						case "4":
							updateVO.setEndDate(In.getStr("종료일(YYYY-MM-DD)"));
							break;
						case "9":
							System.out.println();
							System.out.println("***  수정이 취소 되었습니다. ***");
							break whileloop;
						case "0":
							// DB에 수정 내용 적용
							result = Execute.execute(new NoticeUpdateService(), updateVO);
							if ((Integer)result != 0) {
								System.out.println("*** 수정이 완료되었습니다. ***");
							}
							break whileloop;
						default:
							System.out.println("***************************");
							System.out.println("** 번호를 잘못 입력하셨습니다. **");
							System.out.println("** [0~4,9] 를 입력해주세요. **");
							System.out.println("***************************");
						} // end of switch (item)

					} // end of while(true) - whileloop: 끝
					
					break;
				case "5":
					System.out.println("5. 공지사항 글삭제");
					// 삭제할 글번호 수집
					no = In.getLong("삭제할 글번호");
					
					// DB처리
					result = Execute.execute(new NoticeDeleteService(), no);
					if ((Integer)result != 0) {
						System.out.println();
						System.out.println("***  글이 삭제 되었습니다. ***");
					}
					break;
				default:
					System.out.println("###########################");
					System.out.println("## 메뉴를 잘못 입력하셨습니다. ##");
					System.out.println("## [0~5] 을 입력하세요.     ##");
					System.out.println("###########################");
				} // end of switch(menu)
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
				System.out.println("$%@    <오류 출력> ");
				System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
				// getSimpleName() : 클래스 이름만 보여주는 메서드(패키지는 안보여준다)
				System.out.println("$%@ 타입 : " + e.getClass().getSimpleName());
				// getMessage() : 예외의 내용을 보여주는 메서드
				System.out.println("$%@ 내용 : " + e.getMessage() );
				System.out.println("$%@ 조치 : 데이터 확인해 보세요");
				System.out.println("$%@       계속 오류가 나면 전산담당자에게 문의하세요.");
				System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
			} // end of try~catch
			
			return jsp;

	} // end of execute()
} // end of class
