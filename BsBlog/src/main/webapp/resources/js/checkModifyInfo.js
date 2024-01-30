var mailAuth_check = false
// 주소 검색
function Me_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("modify_member_postcode").value = data.zonecode;
            document.getElementById("modify_member_address").value = data.roadAddress;
        }
    }).open();
} 

// 정규식
// 입력 항목들에 대한 정규표현식 검사 결과를 저장할 변수 선언
// => 각 항목 체크 완료 시 해당 값을 true 로 변경하고, 실패 시 false 로 변경
var password_check_result = false; name_check_result = false
// -------------- 정규표현식을 활용한 입력값 검증 -----------------

// 1. 패스워드에 대한 입력값 검증 : 8 ~ 20자리 영문자, 숫자, 특수문자(!@#$%^&*) 조합
function password_check(password) {
	let regex = /^[A-Za-z0-9!@#$%^&*]{8,20}$/;
	if(!regex.exec(password)) {
		$("#modify_password_check_result").html("영문자(8 ~ 20자), 숫자, 특수문자(!@#$%^&*)<br>필수입니다.");
		$("#modify_password_check_result").css("color", "red");
		password_check_result = false;
	} else {

		// 패스워드 복잡도 검사 추가 => 패스워드 부분 검사
		// 영문 대문자, 소문자, 숫자, 특수문자 조합에 대한 점수 계산 후 
		// 안전, 보통, 위험, 사용불가의 4등급으로 분류하여 결과 출력
		// => 각각의 검사 패턴을 별도로 생성해야함
		let upperCaseRegex = /[A-Z]/; // 대문자
		let lowerCaseRegex = /[a-z]/; // 소문자
		let numRegex = /[0-9]/; // 숫자
		let specialCharRegex = /[!@#$%^&*]/; // 특수문자
		
		let count = 0; // 부분 검사 항목에 대한 점수를 계산하기 위한 변수
		// => 각 검사 항목이 포함되어 있으면 count 값을 1 증가시키기
		// => 주의! 각 항목에 대한 검사는 if 문을 각각 적용해야함! (else if 사용 금지!)
		if(upperCaseRegex.exec(password)) {
			count++;
		} 

		if(lowerCaseRegex.exec(password)) {
			count++;
		} 
		
		if(numRegex.exec(password)) {
			count++;
		} 
		
		if(specialCharRegex.exec(password)) {
			count++;
		} 
		
		// 부분 검사 결과에 대한 출력
		// => 4점 : "안전" 출력(blue)
		// => 3점 : "보통" 출력(green)
		// => 2점 : "위험" 출력(orange)
		// => 1점 이하 : "사용 불가능한 패스워드" 출력(red)
		switch(count) {
			case 4 : 
				$("#modify_password_check_result").html("위험도가 낮은 비밀번호입니다.");
				$("#modify_password_check_result").css("color", "blue");
				password_check_result = true;
				break;
			case 3 : 
				$("#modify_password_check_result").html("위험도가 보통인 비밀번호입니다.");
				$("#modify_password_check_result").css("color", "green");
				password_check_result = true;
				break;
			case 2 : 
				$("#modify_password_check_result").html("위험도가 높은 비밀번호입니다.");
				$("#modify_password_check_result").css("color", "orange");
				password_check_result = true;
				break;
			default :
				$("#modify_password_check_result").html("사용 불가능한 비밀번호입니다.<br>다른 비밀번호를 사용하세요.");
				$("#modify_password_check_result").css("color", "red");
				password_check_result = false;
		}
	}
}

// 2. 이름에 대한 입력값 검증 : 한글 2글자 ~ 10글자
// => 이름이 변경되면 검증 수행하여 패턴과 일치하지 않으면 경고메세지 출력 및 입력값 선택
function name_check(name) {
	let regex = /^[가-힣]{2,10}$/;
	if(!regex.exec(name)) {
		$("#modify_name_check_result").html("이름이 형식에 맞지 않습니다.<br>형식에 맞게 이름을 입력하세요.");
		$("#modify_name_check_result").css("color", "red");
		$("#modify_member_name").select();
		name_check_result = false;
	} else {
		$("#modify_name_check_result").html("이름이 형식에 맞습니다.");
		$("#modify_name_check_result").css("color", "green");
		name_check_result = true;
	}
}

// submit시 작동
function modify_info() {
	if(!name_check_result) {
		alert("이름 양식에 맞추어 입력하세요.");
		$("#modify_member_name").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	}  else if(!password_check_result) {
		alert("비밃번호 양식에 맞추어 입력하세요.");
		$("#modify_member_password").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} 
	return true;
}



