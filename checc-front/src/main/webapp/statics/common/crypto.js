var Crypto = {};

// old key:f5eXEMD8dhAX5pGJ
const _iv = '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0', _key = CryptoJS.enc.Utf8.parse('f5e_E&D8$hAX5pG%');

var cfg = {
	iv : CryptoJS.enc.Utf8.parse(_iv),
	mode : CryptoJS.mode.CBC,
	padding : CryptoJS.pad.Pkcs7
};

function parse() {
	CryptoJS.enc.Utf8.parse(_key);
}

Crypto.encryptAES = function(data) {
	return CryptoJS.AES.encrypt(data, _key, cfg).toString();// 返回的是base64格式的密文
}

Crypto.decryptAES = function(data) {
	return CryptoJS.AES.decrypt(data, _key, cfg).toString(CryptoJS.enc.Utf8);
}
