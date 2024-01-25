var checkEmailAuth = false
// 주소 검색
function Me_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("member_postcode").value = data.zonecode;
            document.getElementById("member_address").value = data.roadAddress;
        }
    }).open();
} 
 
$(document).ready(function(){ 
	// 이메일 인증
	$('#member_mail_auth_check_btn').click(function() {
		const email = $('#member_email').val(); // 이메일 주소값 얻어오기
		console.log('이메일 : ' + email); // 이메일 오는지 확인
		const checkInput = $('#member_email_auth') // 인증번호 입력하는곳 
		
		$.ajax({
			type : 'get',
			url : 'mail_auth_check?email=' + email,
			success : function (data) {
				console.log("data : " +  data);
				checkInput.attr('disabled',false);
				code = data;
				alert('인증번호가 전송되었습니다.')
			}			
		});
	});
	
	// 인증번호 비교 
	$('#member_email_auth').blur(function () {
		const inputCode = $(this).val();
		const $resultMsg = $('#mail-check-warn');
		 
		if(inputCode === code){
			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color','green');
			$('#mail_auth_check').attr('disabled',true);
			$('#userEamil1').attr('readonly',true);
			$('#userEamil2').attr('readonly',true);
			$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	        $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
	        checkEmailAuth = true;
		}else{
			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!');
			$resultMsg.css('color','red');
			checkEmailAuth = false;
		}
	});
});	

// 정규식
// 입력 항목들에 대한 정규표현식 검사 결과를 저장할 변수 선언
// => 각 항목 체크 완료 시 해당 값을 true 로 변경하고, 실패 시 false 로 변경
var checkNameResult = false, checkIdResult = false, checkPasswdResult = false;
var dupIdResult = false;
// -------------- 정규표현식을 활용한 입력값 검증 -----------------

// 1. 아이디에 대한 입력값 검증 : 4 ~ 16자리 영문자, 숫자, 특수문자(-_.) 조합
function checkId(id) {
	let regex = /^[\w-.]{4,16}$/;
	if(!regex.exec(id)) {
		$("#checkIdResult").html("4 ~ 16자리 영문자, 숫자, 특수문자(-_.) 필수!");
		$("#checkIdResult").css("color", "red");
		checkIdResult = false;
	} else {
		$("#checkIdResult").html("사용 가능한 아이디!");
		$("#checkIdResult").css("color", "green");
		checkIdResult = true;
	}
}

// 2. 패스워드에 대한 입력값 검증 : 8 ~ 20자리 영문자, 숫자, 특수문자(!@#$%^&*) 조합
function checkPasswd(passwd) {
	let regex = /^[A-Za-z0-9!@#$%^&*]{8,20}$/;
	if(!regex.exec(passwd)) {
		$("#checkPasswdResult").html("8 ~ 20자리 영문자, 숫자, 특수문자(!@#$%^&*) 필수!");
		$("#checkPasswdResult").css("color", "red");
		checkPasswdResult = false;
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
		if(upperCaseRegex.exec(passwd)) {
			count++;
		} 

		if(lowerCaseRegex.exec(passwd)) {
			count++;
		} 
		
		if(numRegex.exec(passwd)) {
			count++;
		} 
		
		if(specialCharRegex.exec(passwd)) {
			count++;
		} 
		
		// 부분 검사 결과에 대한 출력
		// => 4점 : "안전" 출력(blue)
		// => 3점 : "보통" 출력(green)
		// => 2점 : "위험" 출력(orange)
		// => 1점 이하 : "사용 불가능한 패스워드" 출력(red)
		switch(count) {
			case 4 : 
				$("#checkPasswdResult").html("안전!");
				$("#checkPasswdResult").css("color", "blue");
				checkPasswdResult = true;
				break;
			case 3 : 
				$("#checkPasswdResult").html("보통!");
				$("#checkPasswdResult").css("color", "green");
				checkPasswdResult = true;
				break;
			case 2 : 
				$("#checkPasswdResult").html("위험!");
				$("#checkPasswdResult").css("color", "orange");
				checkPasswdResult = true;
				break;
			default :
				$("#checkPasswdResult").html("사용 불가능한 패스워드!");
				$("#checkPasswdResult").css("color", "red");
				checkPasswdResult = false;
		}
	}
}

// 3. 이름에 대한 입력값 검증 : 한글 2글자 ~ 10글자
// => 이름이 변경되면 검증 수행하여 패턴과 일치하지 않으면 경고메세지 출력 및 입력값 선택
function checkName(name) {
	let regex = /^[가-힣]{2,10}$/;
	if(!regex.exec(name)) {
		$("#checkNameResult").html("이름이 형식에 맞지 않음!");
		$("#checkNameResult").css("color", "red");
		$("#member_name").select();
		checkNameResult = false;
	} else {
		checkNameResult = true;
	}
}

// submit시 작동
function checkForm() {
	if(!checkNameResult) {
		alert("이름 양식에 맞춰주세요");
		$("#member_name").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!checkIdResult) {
		alert("아이디 양식에 맞춰주세요");
		$("#member_id").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!checkPasswdResult) {
		alert("패스워드 양식에 맞춰주세요");
		$("#member_pass").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!checkEmailAuth) {
		alert("이메일 인증번호가 일치하지 않습니다");
		$("#member_email_auth").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!dupIdResult) {
		alert("아이디 중복을 확인하여 주세요");
		$("#member_id").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} 
	return true;
}

// 아이디 중복 조회
function dupId() {
	alert('되니');
	var member_id = $('#member_id').val(); //id값이 "member_id"인 입력란의 값을 저장
	$.ajax({
		url: 'idCheck', //Controller에서 요청 받을 주소
		type: 'post', //POST 방식으로 전달
		data: { member_id : member_id },
		success: function(memberCheck) { //컨트롤러에서 넘어온 checkCount값을 받는다 
			if (memberCheck == 0) { //memberCheck가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
				$("#checkIdResult").html("사용 가능한 아이디 입니다");
				$("#checkIdResult").css("color", "blue");
				dupIdResult = true;
				
			} else { // memberCheck가 1일 경우 -> 이미 존재하는 아이디
				$("#checkIdResult").html("이미 존재하는 아이디 입니다");
				$("#checkIdResult").css("color", "red");
//				alert("이미 존재하는 아이디 입니다");
//				alert("아이디를 다시 입력해주세요");
				$('#member_id').val('').focus();
				dupIdResult = false;
			}
		},
		error: function() {
			alert("에러입니다");
		}
	});
};


