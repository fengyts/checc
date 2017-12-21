$(document).ready(function() {
	
	$("#is_only_me").on('click', function() {
		var _isOnlyMe = $("#isOnlyMe").val();
		if(0 == _isOnlyMe){
			$("#is_only_me a").text("看全部出价");
			$("#isOnlyMe").val(1);
		} else {
			$("#is_only_me a").text("只看我出价");
			$("#isOnlyMe").val(0);
		}
		
		$("#auctionListForm").submit();
		
	});
	
	
});