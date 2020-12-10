function pwChk() {
	var pw = $("#pw").val();
	var pw2 = $("#pw2").val();
	if(pw != pw2) {
		$("#pw_chk").css('display', 'inline-block');
	} else if(pw == pw2) {
		$("#pw_chk").css('display', 'none');
	}
}

function pwChk_submit() {
	var pw = $("#pw").val();
	var pw2 = $("#pw2").val();
	if(pw != pw2) {
		return false;
	}
	
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	 if(pw.length < 8 || pw.length > 20){
		alert("8자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	} else if(pw.search(/\s/) != -1){
 		alert("비밀번호는 공백 없이 입력해주세요.");
 		return false;
 	} else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
		alert("영문, 숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
		return false;
	} else {
		return true;
	}
}

function id_Check() {
	window.open("/Project/view/member_chk/idChk.jsp", "id_chk", "width=400, height=300");
}

function search_id() {
	window.open("/Project/view/member_chk/searchID.jsp", "search_id", "width=400, height=300");
}

function search_pw() {
	window.open("/Project/view/member_chk/searchPW.jsp", "search_pw", "width=400, height=300");
}

function input_pw() {
	window.open("/Project/view/member_chk/inputPW.jsp", "input_pw", "width=400, height=300");
}

function confirmChk(confirm_value) {
	var confirmNum = $("#confirmNum").val();
	if(confirmNum != confirm_value) {
		alert("인증번호가 일치하지 않습니다. 다시 입력해주세요.");
		$('#confirmNum').val('');
		return false;
	}
}

function getPoint(point_value) {
	var point = $('#point_input').val();
	var num = point.search(/[0-9]/g);
	
	if(num<0 || point>point_value || point<0 || point<5000) {
		$('#point_input').val('0');
	} else {
		$("#point").html(`${point}p`);
	}	
}

function allPoint(point_value) {
	var point = $('#point_input').val();
	
	if(point>=5000) {
		$('#point_input').val(`${point_value}`);
		$("#point").html(`${point_value}p`);
	} else {
		alert("5,000 포인트 이상부터 사용 가능합니다.");
	}
}