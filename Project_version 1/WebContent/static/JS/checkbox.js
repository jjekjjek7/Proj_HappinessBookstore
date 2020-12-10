$("#chk_all").click(function(){
	if($("#chk_all").is(":checked")){
		$(".chkBook").prop("checked", true);
	} else {
		$(".chkBook").prop("checked", false);
	}
});
$(".chkBook").click(function(){
	if($("input[name='chkBook']:checked").length == $("input[name=chkBook]").length) {
		$("#chk_all").prop("checked", true);
	} else {
		$("#chk_all").prop("checked", false);
	}
});

$(".delete_btn").click(function() {
	var confirm_val = confirm("정말 삭제하시겠습니까?");

	if (confirm_val) {
		var checkArr = new Array();

		$("input[class='chkBook']:checked").each(function() {
			checkArr.push($(this).attr("data-cartNum"));
		});
		
		checkArr  = checkArr.filter(function(item) {
			return item !== null && item !== undefined && item !== '';
		});
		
		location.href = "/Project/view/member/cartDelete.do?chkArr="+checkArr;
	}
});

$(".select_btn").click(function() {
	var checkArr = new Array();

	$("input[class='chkBook']:checked").each(function() {
		checkArr.push($(this).attr("data-cartNum"));
	});
	
	checkArr  = checkArr.filter(function(item) {
		return item !== null && item !== undefined && item !== '';
	});
		
	location.href = "/Project/view/member/order.do?chkArr="+checkArr;
});

$(".all_btn").click(function() {
	var checkArr = new Array();
	
	$(".chkBook").prop("checked", true);

	$("input[class='chkBook']:checked").each(function() {
		checkArr.push($(this).attr("data-cartNum"));
	});
	
	checkArr  = checkArr.filter(function(item) {
		return item !== null && item !== undefined && item !== '';
	});
	
	location.href = "/Project/view/member/order.do?chkArr="+checkArr;
});

function updateCnt(no, cartNo) {
	var cnt = $(`#cnt${no}`).val();
	location.href = "/Project/view/member/cartModify.do?cnt="+cnt+"&cartNo="+cartNo;
}
