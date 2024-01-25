var mailAuth_check = false
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
	$('#mailAuth_checkBtn').click(function() {
		const email = $('#member_email').val(); // 이메일 주소값 얻어오기
		console.log('이메일 : ' + email); // 이메일 오는지 확인
		const checkInput = $('#member_email_auth') // 인증번호 입력하는곳 
		
		$.ajax({
			type : 'get',
			url : 'mailAuth_check?email=' + email,
			success : function (data) {
				console.log("data : " +  data);
				checkInput.attr('disabled',false);
				code = data;
				alert('인증번호가 전송되었습니다.')
			}			
		});
	});
	
	// 인증번호 비교 
	$('#mailAuth').blur(function () {
		const inputCode = $(this).val();
		const $resultMsg = $('#mailAuth_check_warn');
		 
		if(inputCode === code){
			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color','green');
			$('#mailAuth').attr('disabled',true);
			$('#userEamil1').attr('readonly',true);
			$('#userEamil2').attr('readonly',true);
			$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	        $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
	        mailAuth_check = true;
		}else{
			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인하고 입력해주세요.');
			$resultMsg.css('color','red');
			mailAuth_check = false;
		}
	});
});	

// 정규식
// 입력 항목들에 대한 정규표현식 검사 결과를 저장할 변수 선언
// => 각 항목 체크 완료 시 해당 값을 true 로 변경하고, 실패 시 false 로 변경
var id_check_result = false, password_check_result = false; name_check_result = false
var idDup_check_result = false;
// -------------- 정규표현식을 활용한 입력값 검증 -----------------

// 1. 아이디에 대한 입력값 검증 : 4 ~ 16자리 영문자, 숫자, 특수문자(-_.) 조합
function id_check(id) {
	let regex = /^[\w-.]{4,16}$/;
	if(!regex.exec(id)) {
		$("#id_check_result").html("4 ~ 16자리 영문자, 숫자, 특수문자(-_.) 필수입니다.");
		$("#id_check_result").css("color", "red");
		id_check_result = false;
	} else {
		$("#id_check_result").html("아이디 양식에 적합합니다.");
		$("#id_check_result").css("color", "green");
		id_check_result = true;
	}
}

// 2. 패스워드에 대한 입력값 검증 : 8 ~ 20자리 영문자, 숫자, 특수문자(!@#$%^&*) 조합
function Password_check(password) {
	let regex = /^[A-Za-z0-9!@#$%^&*]{8,20}$/;
	if(!regex.exec(password)) {
		$("#Password_check_result").html("8 ~ 20자리 영문자, 숫자, 특수문자(!@#$%^&*) 필수입니다.");
		$("#Password_check_result").css("color", "red");
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
				$("#Password_check_result").html("위험도가 낮은 비밀번호입니다.");
				$("#Password_check_result").css("color", "blue");
				password_check_result = true;
				break;
			case 3 : 
				$("#Password_check_result").html("위험도가 보통인 비밀번호입니다.");
				$("#Password_check_result").css("color", "green");
				password_check_result = true;
				break;
			case 2 : 
				$("#Password_check_result").html("위험도가 높은 비밀번호입니다.");
				$("#Password_check_result").css("color", "orange");
				password_check_result = true;
				break;
			default :
				$("#Password_check_result").html("사용 불가능한 비밀번호입니다. 다른 비밀번호를 사용하세요.");
				$("#Password_check_result").css("color", "red");
				password_check_result = false;
		}
	}
}

// 3. 이름에 대한 입력값 검증 : 한글 2글자 ~ 10글자
// => 이름이 변경되면 검증 수행하여 패턴과 일치하지 않으면 경고메세지 출력 및 입력값 선택
function name_check(name) {
	let regex = /^[가-힣]{2,10}$/;
	if(!regex.exec(name)) {
		$("#name_check_result").html("이름이 형식에 맞지 않습니다. 형식에 맞게 이름을 입력하세요.");
		$("#name_check_result").css("color", "red");
		$("#member_name").select();
		name_check_result = false;
	} else {
		$("#name_check_result").html("이름이 형식에 맞습니다.");
		$("#name_check_result").css("color", "green");
		name_check_result = true;
	}
}

// submit시 작동
function form_check() {
	if(!name_check_result) {
		alert("이름 양식에 맞추어 입력하세요.");
		$("#member_name").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!id_check_result) {
		alert("아이디 양식에 맞추어 입력하세요.");
		$("#member_id").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!password_check_result) {
		alert("비밃번호 양식에 맞추어 입력하세요.");
		$("#member_pass").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!mailAuth_check) { // * 인증번호 일치 여부 공부
		alert("이메일 인증번호가 일치하지 않습니다. 다시 확인하여 입력하세요.");
		$("#member_email_auth").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} else if(!idDup_check_result) {
		alert("아이디 중복을 확인하여 주세요.");
		$("#member_id").select();
		return false; // 현재 폼의 submit 동작을 중단하기 위해 false 리턴
	} 
	return true;
}

// 아이디 중복 조회
function idDup_check() {
	var member_id = $('#member_id').val(); //id값이 "member_id"인 입력란의 값을 저장
	$.ajax({
		url: 'idDup_check', //Controller에서 요청 받을 주소
		type: 'post', //POST 방식으로 전달
		data: { member_id : member_id },
		success: function(idDup_checkCount) { //컨트롤러에서 넘어온 checkCount값을 받는다 
			if (idDup_checkCount == 0) { //memberCheck가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
				alert('사용 가능한 아이디입니다.');
				$('#member_id').val(member_id);
				idDup_check_result = true;
				
			} else { // memberCheck가 1일 경우 -> 이미 존재하는 아이디
				alert('이미 존재하는 아이디 입니다. 다른 아이디를 사용해 주세요.');
				$('#member_id').val('').focus();
				idDup_check_result = false;
			}
		},
		error: function() {
			alert("에러입니다");
		}
	});
};


