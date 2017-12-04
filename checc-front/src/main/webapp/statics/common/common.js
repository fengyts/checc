function validMobile(mobile, callback) {
	if (Utils.isEmpty(mobile)) {
		return false;
	}
	$.get('validateMobile', {
		"mobile" : mobile
	}, callback);
}

/**
 * 禁止页面后退
 */
function forbidBack() {
	history.pushState(null, null, document.URL);
	window.addEventListener('popstate', function() {
		history.pushState(null, null, document.URL);
	});
}
