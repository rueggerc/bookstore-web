

function checkCurrency(thisValue, e) {
	// Disable the user from entering anything other than numbers.
	// Allow: backspace, delete, tab, escape, decimal is 190
	if ($.inArray(e.keyCode, [46, 8, 9, 27, 110]) !== -1 ||
		// Allow: Ctrl+A, Command+A
		(e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
		// Allow: home, end, left, right, down, up
		(e.keyCode >= 35 && e.keyCode <= 40)) {
		// let it happen, dont do anything
		return;
	}
	
	// Prevent enter key
	if (e.keyCode === 13) {
		e.preventDefault();
		return false;
	}
	
	// Do not allow more than one decimal point
	if (e.keyCode === 190) {
		var dotMatches = thisValue.split(".").length - 1;
		if (dotMatches === 1) {
			e.preventDefault();
			return false;
		} else {
			return;
		}
	}
	
	// Ensure that it is a number and stop the keypress
	if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
		e.preventDefault();
	}
	
	// Do not allow more than 2 digits past the decimal point
	return allowOnly2Decimals(thisValue, e);
}

function allowOnly2Decimals(thisValue, e) {
	if (e.keyCode >= 48 && e.keyCode <= 57) {
		var dotMatches = thisValue.split(".").length - 1;
		if (dotMatches === 0) {
			return;
		}
		var lastIndex = thisValue.lastIndexOf(".");
		var valueLength = thisValue.length;
		if ((valueLength - lastIndex) > 2) {
			e.preventDefault();
			return false;
		}
		return;
	}
}


function checkAjaxResponse() {
	var ajaxResult = document.getElementById('formID:requestResultState').value;
	if (ajaxResult === "fail") {
		redirectToErrorPage();
		return false;
	}
	return true;
}