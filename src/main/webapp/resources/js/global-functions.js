
var rueggerllc = (function() {
	
	var foo = function(arg) {
		console.log("foo function:" + arg);
	};
	
	var bar = function(arg) {
		console.log("bar function: " + arg);
	};
	
	
	var utils = {
			validatePercent: function() {
				console.log("validatePercent");
			},
			validateCurrency: function() {
				console.log("validateCurrency");
			}
	};
	
	// Expose functions
	return {
		foo: foo,
		bar: bar,
		validatePercent: utils.validatePercent,
		validateCurrency: utils.validateCurrency
	}
	
}());
