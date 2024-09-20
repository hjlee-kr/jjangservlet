package com.jjangplay.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.jjangplay.board.dao.BoardDAO;
import com.jjangplay.board.service.BoardDeleteService;
import com.jjangplay.board.service.BoardListService;
import com.jjangplay.board.service.BoardUpdateService;
import com.jjangplay.board.service.BoardViewService;
import com.jjangplay.board.service.BoardWriteService;
import com.jjangplay.boardreply.dao.BoardReplyDAO;
import com.jjangplay.boardreply.service.BoardReplyDeleteService;
import com.jjangplay.boardreply.service.BoardReplyListService;
import com.jjangplay.boardreply.service.BoardReplyUpdateService;
import com.jjangplay.boardreply.service.BoardReplyWriteService;
import com.jjangplay.goods.dao.GoodsDAO;
import com.jjangplay.goods.service.GoodsDeletePriceService;
import com.jjangplay.goods.service.GoodsDeleteService;
import com.jjangplay.goods.service.GoodsImageChangeService;
import com.jjangplay.goods.service.GoodsListService;
import com.jjangplay.goods.service.GoodsUpdatePriceService;
import com.jjangplay.goods.service.GoodsUpdateService;
import com.jjangplay.goods.service.GoodsViewPriceService;
import com.jjangplay.goods.service.GoodsViewService;
import com.jjangplay.goods.service.GoodsWritePriceService;
import com.jjangplay.goods.service.GoodsWriteService;
import com.jjangplay.image.dao.ImageDAO;
import com.jjangplay.image.service.ImageChangeService;
import com.jjangplay.image.service.ImageDeleteService;
import com.jjangplay.image.service.ImageListService;
import com.jjangplay.image.service.ImageUpdateService;
import com.jjangplay.image.service.ImageViewService;
import com.jjangplay.image.service.ImageWriteService;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.service.MemberChangeGradeNoService;
import com.jjangplay.member.service.MemberChangePhotoService;
import com.jjangplay.member.service.MemberChangeStatusService;
import com.jjangplay.member.service.MemberCheckIdService;
import com.jjangplay.member.service.MemberConUpdateService;
import com.jjangplay.member.service.MemberDeleteService;
import com.jjangplay.member.service.MemberListService;
import com.jjangplay.member.service.MemberLoginService;
import com.jjangplay.member.service.MemberUpdateService;
import com.jjangplay.member.service.MemberViewService;
import com.jjangplay.member.service.MemberWriteService;
import com.jjangplay.notice.dao.NoticeDAO;
import com.jjangplay.notice.service.NoticeDeleteService;
import com.jjangplay.notice.service.NoticeListService;
import com.jjangplay.notice.service.NoticeUpdateService;
import com.jjangplay.notice.service.NoticeViewService;
import com.jjangplay.notice.service.NoticeWriteService;
import com.jjangplay.shop.dao.ShopDAO;
import com.jjangplay.shop.service.ShopListService;

public class Init {

	// Service를 생성해서 저장하는 객체 - <URI, Service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// dao를 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	// 자바에서 클래스를 사용하기 위해서는 주소할당이 필요하다
	// new로 메모리를 할당해서 사용합니다.
	// new할때마다 메모리 공간이 할당이 되어 서버에서는 한번만 할당해서
	// 그 공간을 공유하여 사용합니다.
	// 한번만 new한값을 key값에 저장해 두고 사용합니다.
	
	// static 메서드에서 초기화 해준다. (메모리 로드될때 1번만 실행됨)
	static {
		// dao를 생성
		daoMap.put("boardDAO", new BoardDAO());
		// 1. 일반게시판 service 생성
		// new BoardListService()를 하면 주소값이 나온다.
		// key: "/board/list.do", value: BoardListService의 주소값
		// hashMap에서 key값에 저장할 때는 put
		// 저장된 값을 꺼낼때는 get을 사용합니다.
		serviceMap.put("/board/list.do", new BoardListService());
		serviceMap.put("/board/view.do", new BoardViewService());
		serviceMap.put("/board/write.do", new BoardWriteService());
		serviceMap.put("/board/update.do", new BoardUpdateService());
		serviceMap.put("/board/delete.do", new BoardDeleteService());
		
		// 조립 dao->service
		serviceMap.get("/board/list.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/view.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/write.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/update.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/delete.do").setDAO(daoMap.get("boardDAO"));
		// 1-1 일반게시판 댓글
		// dao 생성
		daoMap.put("boardReplyDAO", new BoardReplyDAO());
		// service 생성
		serviceMap.put("/boardreply/list.do", new BoardReplyListService());
		serviceMap.put("/boardreply/write.do", new BoardReplyWriteService());
		serviceMap.put("/boardreply/update.do", new BoardReplyUpdateService());
		serviceMap.put("/boardreply/delete.do", new BoardReplyDeleteService());
		// 조립 dao->service
		serviceMap.get("/boardreply/list.do").setDAO(daoMap.get("boardReplyDAO"));
		serviceMap.get("/boardreply/write.do").setDAO(daoMap.get("boardReplyDAO"));
		serviceMap.get("/boardreply/update.do").setDAO(daoMap.get("boardReplyDAO"));
		serviceMap.get("/boardreply/delete.do").setDAO(daoMap.get("boardReplyDAO"));
		
		// 2. 공지사항
		// dao를 생성
		daoMap.put("noticeDAO", new NoticeDAO());
		// 서비스생성
		serviceMap.put("/notice/list.do", new NoticeListService());
		serviceMap.put("/notice/view.do", new NoticeViewService());
		serviceMap.put("/notice/write.do", new NoticeWriteService());
		serviceMap.put("/notice/update.do", new NoticeUpdateService());
		serviceMap.put("/notice/delete.do", new NoticeDeleteService());
		
		// 조립 dao->service
		serviceMap.get("/notice/list.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/view.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/write.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/update.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/delete.do").setDAO(daoMap.get("noticeDAO"));
		
		// 3. 회원관리
		// dao를 생성
		daoMap.put("memberDAO", new MemberDAO());
		// 서비스생성
		serviceMap.put("/member/login.do", new MemberLoginService());
		serviceMap.put("/member/list.do", new MemberListService());
		serviceMap.put("/member/view.do", new MemberViewService());
		serviceMap.put("/member/write.do", new MemberWriteService());
		serviceMap.put("/member/update.do", new MemberUpdateService());
		serviceMap.put("/member/delete.do", new MemberDeleteService());
		serviceMap.put("/ajax/checkId.do", new MemberCheckIdService());
		serviceMap.put("/member/changeGradeNo.do", new MemberChangeGradeNoService());
		serviceMap.put("/member/changeStatus.do", new MemberChangeStatusService());
		serviceMap.put("/member/changePhoto.do", new MemberChangePhotoService());
		serviceMap.put("/member/conUpdate.do", new MemberConUpdateService());
		// 조립 dao->service
		serviceMap.get("/member/login.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/list.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/view.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/write.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/update.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/delete.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/checkId.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changeGradeNo.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changeStatus.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changePhoto.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/conUpdate.do").setDAO(daoMap.get("memberDAO"));
		
		
		// dao를 생성
		daoMap.put("imageDAO", new ImageDAO());
		// 4. 이미지게시판 service 생성
		serviceMap.put("/image/list.do", new ImageListService());
		serviceMap.put("/image/view.do", new ImageViewService());
		serviceMap.put("/image/write.do", new ImageWriteService());
		serviceMap.put("/image/update.do", new ImageUpdateService());
		serviceMap.put("/image/delete.do", new ImageDeleteService());
		serviceMap.put("/image/imageChange.do", new ImageChangeService());
		// 조립 dao->service
		serviceMap.get("/image/list.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/view.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/write.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/update.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/delete.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/imageChange.do").setDAO(daoMap.get("imageDAO"));
		
		
		// 상품관리
		// dao생성
		daoMap.put("goodsDAO", new GoodsDAO());
		// 서비스생성
		serviceMap.put("/goods/list.do", new GoodsListService());
		serviceMap.put("/goods/view.do", new GoodsViewService());
		serviceMap.put("/goods/viewPrice.do", new GoodsViewPriceService());
		serviceMap.put("/goods/write.do", new GoodsWriteService());
		serviceMap.put("/goods/writePrice.do", new GoodsWritePriceService());
		serviceMap.put("/goods/update.do", new GoodsUpdateService());
		serviceMap.put("/goods/updatePrice.do", new GoodsUpdatePriceService());
		serviceMap.put("/goods/delete.do", new GoodsDeleteService());
		serviceMap.put("/goods/deletePrice.do", new GoodsDeletePriceService());
		serviceMap.put("/goods/imageChange.do", new GoodsImageChangeService());
		// 조립
		serviceMap.get("/goods/list.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/view.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/viewPrice.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/write.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/writePrice.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/update.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/updatePrice.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/delete.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/deletePrice.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/imageChange.do").setDAO(daoMap.get("goodsDAO"));
		
		//쇼핑몰
		//dao생성
		daoMap.put("shopDAO", new ShopDAO());
		//서비스생생
		serviceMap.put("/shop/list.do", new ShopListService());
		//조립
		serviceMap.get("/shop/list.do").setDAO(daoMap.get("shopDAO"));
	}
	
	
	public static Service get(String uri) {
		// uri key값으로 주소값을 리턴한다.
		return serviceMap.get(uri);
	}
}








