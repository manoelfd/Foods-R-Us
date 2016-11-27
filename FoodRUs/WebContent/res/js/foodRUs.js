function sendRequest(address, data) {
	request = new XMLHttpRequest();
	request.open("post", (address + "?" + data), true);
	 request.onreadystatechange = function() {handler(request);};
	 request.send(null); ;
}

function addItem(item) {
	alert(item);

	return true;
	/*
	var address = "http://localhost:4413/mcApp1/payment.do";
	var data = "principle=" + p + "&interest=" + i + "&amort=" + a;
	loadDoc(address, data);
	show("Result", "UI");
	return false;
	*/
}


