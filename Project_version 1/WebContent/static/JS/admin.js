function update_comment(no, email) {
	var comment_value = document.getElementById('comment').value;
	location.href = `comment.do?no=${no}&comment=${comment_value}&email=${email}`;
}

function delete_Member(id) {
	if (confirm("정말 삭제하시겠습니까?") == true){
		location.href = "/Project/view/member/memberDelete.do?id="+id;
	} else{
		return false;
	}
}

function delete_qna(no) {
	if (confirm("정말 삭제하시겠습니까?") == true){
		location.href = "/Project/view/help/help_delete.do?no="+no;
	} else{
		return false;
	}
}

function delete_event(no) {
	if (confirm("정말 삭제하시겠습니까?") == true){
		location.href = "/Project/view/event/event_delete.do?no="+no;
	} else{
		return false;
	}
}

function update_state(no) {
	var state = $('#event_state').val();
	console.log(state);
	location.href = `/Project/view/event/event_update.do?no=${no}&state=${state}`;
}

function updateState(order_no) {
	var state = $('#state').val();
	location.href = `/Project/view_admin/member/admin_Order_Modify.do?order_no=${order_no}&state=${state}`;
}

function searchState(search, search_value) {
	var state = $('#state_search').val();
	location.href = `/Project/view_admin/member/admin_Order_List.do?search=${search}&search_value=${search_value}&state=${state}`;
}

function search_State(search, search_value) {
	var state = $('#state_search').val();
	location.href = `/Project/view/help/main.do?search=${search}&search_value=${search_value}&state=${state}`;
}

function event_state() {
	var state = $('#event_state').val();
	location.href = `/Project/view/event/event.do?state=${state}`;
}
