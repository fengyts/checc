function validMobile(mobile, callback) {
	if (Utils.isEmpty(mobile)) {
		return false;
	}
	$.get('validateMobile', {
		"mobile" : mobile
	}, callback);
}

