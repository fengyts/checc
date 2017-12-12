$(document).ready(function() {
	$(".itemsal").on('click', function(){
		var _href = $(this).attr("href");
		$(this).attr("href", _href + "?reqTime=" + new Date().getTime());
	});
});