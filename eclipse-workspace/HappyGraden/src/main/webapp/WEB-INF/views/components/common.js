function blockbg() {
	$.isLoading({
		text : "Loading"
	});
}

function ajaxRedirect(url) {
	$.ajax({
		type : "GET",
		url : url,
		beforeSend: blockbg(),
		success : function(data, textStatus, request) {
			
			var msgFlag = request.getResponseHeader('msgFlag');
			if( "1" == msgFlag ) {
				$(".message-info").html(data);
				var html1 = $('.main_content').find('.message-info').html()
				$('.main_content .content-bottom').html(html1);
			} else {
				var content = $(data).find('.body-content');
				$(".main_content").html(content);
			}
			window.history.pushState('', '', url);
		},
		error : function(xhr, textStatus, error) {
			console.log(xhr);
			console.log(textStatus);
			console.log(error);
		}
	});
}
