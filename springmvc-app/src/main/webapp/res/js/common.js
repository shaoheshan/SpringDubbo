var PINDEX=""
var SUC  = "200";
var FAIL = "999";
var FAIL_400 = "400";
var FAIL_500 = "500";
var OUT = "out";




function fmtTime(ms, formatter) {
	if(!ms) {
		return;
	}
	var date = new Date();
	date.setTime(ms);
	var year = date.getFullYear();
	var month = toDouble(date.getMonth() + 1);
	var day = toDouble(date.getDate());
	var hour = toDouble(date.getHours());
	var tHour = parseInt(hour) < 12? parseInt(hour): parseInt(hour) - 12;
	var minute = toDouble(date.getMinutes());
	var second = toDouble(date.getSeconds());
	switch (formatter) {
		case 'yyyy-mm-dd':
			return year + '-' + month + '-' + day;
			break;
		case 'yyyy-mm-dd hh:mm:ss':
			return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
			break;
		case 'yyyy-mm-dd HH:mm:ss':
			return year + '-' + month + '-' + day + ' ' + tHour + ':' + minute + ':' + second;
			break;
		default:
			return;
			break;
	}
}

function toDouble (num) {
	return num < 10? '0' + num: num;
}