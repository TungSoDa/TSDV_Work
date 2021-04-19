function submitSearch() {
	var searchedData = $("#search-box").val().trim();
	if (searchedData.length !== 0) {
		getUserData(searchedData);
	}
}

function getUserData(searchedData) {
	var path = serverContextPath + 'admin/userAPI?userName=' + searchedData;
	$.ajax({
		type : "GET",
		url : path,
		timeout : 5000,
		success : function(data) {
			console.log(data);
			if (data != null) {
				if (data.statusCode == 200) {
					refreshTable(data.body);
				}
			}

		},
		error : function(e) {
			console.log("getUserData() - error:", e);
		},
		done : function(e) {
		}
	});
}

function refreshTable(data) {
	$('#userTable tbody').children().remove()
	$.each(data, function(i, user) {
		console.log(user.userName);
		var userRow = '<tr>' 
			+ '<td>' + user.userId + '</td>' 
			+ '<td>' + user.userName + '</td>'
			+ '</tr>';

		$('#userTable tbody').append(userRow);
	});
	$( "#userTable tbody tr:odd" ).addClass("info");
	$( "#userTable tbody tr:even" ).addClass("success");
}