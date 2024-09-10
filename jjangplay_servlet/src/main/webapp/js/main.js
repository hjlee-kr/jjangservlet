/**
 * 
 */

//이벤트 처리
$(function(){
	$(".dataRow").click(function(){
		// 글번호 수집
		let no = $(this).find(".no").text();
		// 모듈체크
		if($(this).hasClass("board")) {
			location = "/board/view.do?no=" + no + "&inc=1";
		}
		else if	($(this).hasClass("notice")) {
			location = "/notice/view.do?no=" + no;
		}
		else if	($(this).hasClass("image")) {
			location = "/image/view.do?no=" + no;
		}
		
	});
});