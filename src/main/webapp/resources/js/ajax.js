
// ======================== AJAX FUNCTIONS BEGIN
function ajaxEventHandler(data) {
  if ((data.status === "complete" && !data.responseText) || data.type === "error") {
	  alert("AJAX EVENT HANDLER ERROR=" + data.responseText);
	  redirectToErrorPage();
  }
} 

jsf.ajax.addOnEvent(ajaxEventHandler);
jsf.ajax.addOnError(ajaxEventHandler);

function redirectToErrorPage() {
   window.location = 'errorPage.xhtml?errorPageMessage=error_system_down';
}


// AJAX Cursor
var defaultCursor;
function ajaxWaitCursorOn() {
	var parentDiv = document.createElement("div");
	parentDiv.style.overflow="hidden";
	parentDiv.style.position="absolute";
	parentDiv.style.left = "0px";
	parentDiv.style.top = "0px";
	parentDiv.style.width = "100%";
	parentDiv.style.height = "100%";
	var childDiv = document.createElement("div");
	childDiv.style.width = "200%";
	childDiv.style.height = "200%";
	parentDiv.appendChild(childDiv);
	document.body.appendChild(parentDiv);
	
	var htmlElem = document.getElementsByTagName('html')[0];
	htmlElem.className = 'ajaxWait';
	
	parentDiv.scrollLeft = 1;
	document.body.removeChild(parentDiv);
}

function ajaxWaitCursorOff() {
	var parentDiv = document.createElement("div");
	parentDiv.style.overflow="hidden";
	parentDiv.style.position="absolute";
	parentDiv.style.left = "0px";
	parentDiv.style.top = "0px";
	parentDiv.style.width = "100%";
	parentDiv.style.height = "100%";
	var childDiv = document.createElement("div");
	childDiv.style.width = "200%";
	childDiv.style.height = "200%";
	parentDiv.appendChild(childDiv);
	document.body.appendChild(parentDiv);
	
	var htmlElem = document.getElementsByTagName('html')[0];
	htmlElem.className = '';
	
	parentDiv.scrollLeft = 1;
	document.body.removeChild(parentDiv);
}


// ======================== AJAX FUNCTIONS END 

