var $ = function(a, b) {
	var ID = document.getElementById(a);
	var OBJ = (b) ? ID.getElementsByTagName(b) : ID;
	return OBJ;
};
var n = 0;
var tab = function(MENU, BODY) {
	var l = MENU.length;
	for ( var i = 0; i < l; i++) {
		MENU[i].onclick = function(a) {
			return function() {
				MENU[n].className = "label";
				BODY[n].style.display = "none";
				MENU[a].className = "label label-selected";
				BODY[a].style.display = "block";
				n = a;
			};
		}(i);
	}
};