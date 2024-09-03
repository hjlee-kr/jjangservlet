/**
 * 
 */

console.log("[boardInputUtil.js] 파일 로딩......");

//입력데이터가 비어있는 경우 true리턴 -> 서버에는 false를 전달
//isEmpty(객체이름(선택자), 항목이름, 양쪽공백삭제여부)
function isEmpty(objName, name, isTrim) {
	console.log("objName = " + objName + ", name = " + name +
		", isTrim = " + isTrim);
	let str = $(objName).val();
//	if (isTrim) str = str.trim();
//	$(objName).val(str);
	if (isTrim) {
		str = str.trim();
		$(objName).val(str);
	}
	if (str == "") {
		alert(name + "은(는) 반드시 입력해야 합니다.");
		$(objName).focus();
		return true;
	}
	
}

// 길이 제한 메서드
function lengthCheck(objName, name, min, max, isTrim) {
	console.log("objName = " + objName + ", name = " +
		name + ", min = " + min, ", max = " + max +
		", isTrim = " + isTrim);
		
	let str = $(objName).val();
	if (isTrim) {
		str = str.trim();
		// 공백제거한 값을 다시 넣는다.
		$(objName).val(str);
	}
	
	let len = str.length;
	if (len < min || len > max) {
		alert(name + "은(는) " + min + "자부터 " + max +
			"자까지 입력하셔야 합니다.");
		$(objName).focus();
		return true;
	}
}













