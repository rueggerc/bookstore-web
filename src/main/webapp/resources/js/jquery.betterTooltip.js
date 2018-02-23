
/*-------------------------------------------------------------------------------
	A Better jQuery Tooltip
	Version 1.0
	By Jon Cazier
	jon@3nhanced.com
	01.22.08
-------------------------------------------------------------------------------*/

$.fn.protoTooltip = function(options){
	
	
	
	/* Setup the options for the tooltip that can be 
	   accessed from outside the plugin              */
	var defaults = {
		speed: 200,
		delay: 300,
		serviceID: 'NoService'
	};
	
	var options = $.extend(defaults, options);
	
	getTip = function() {
	var tTip = $('<div id="toolTipContainer" class="toolTipContainer">'
		      + '<table width="" border="0" cellspacing="0" cellpadding="0" align="center" class="toolTipPopup">'
		      + '<tr>'
		      + '   <td class="corner topLeft"></td>'
		      + '   <td class="top"></td>'
		      + '   <td class="corner topRight"></td>'
		      + '</tr>'
		      + '<tr>'
		      + '   <td class="left">&nbsp;</td>'
		      + '   <td><div id="toolTipContent" class="toolTipContent"></div></td>'
		      + '   <td class="right">&nbsp;</td>'
		      + '</tr>'
		      + '<tr>'
		      + '   <td class="corner bottomLeft">&nbsp;</td>'
		      + '   <td class="bottom">&nbsp;</td>'
		      + '   <td class="corner bottomRight"></td>'
		      + '</tr>'
		      + '</table>'
		      + '</div>');
    	return tTip;
	}
	$("body").prepend(getTip());
	

	
	/* Give each item with the class associated with 
	   the plugin the ability to call the tooltip    */
	$(this).each(function(){
		
		// var hrefValue = $(this).attr('href');
		var hrefValue = $(this).attr('id');
		var $this = $(this);
		
		var tip = $('.toolTipContainer');
		var tipInner = $('.toolTipContent');
		
		var tTitle = hrefValue;
		this.title = "";
		
		var offset = $(this).offset();
		var tLeft = offset.left;
		var tTop = offset.top;
		var tWidth = $this.width();
		var tHeight = $this.height();
		
		/* Mouse over and out functions*/
		$this.hover(
			function() {
				// var zapBox = $('.messageBox');
        	    // zapBox.html("MOUSE IN");
				ajaxCall(options.serviceID, hrefValue, tipInner);
				positionTip(tTop, tLeft);
				setTimer();
			}, 
			function() {
				// var zapBox = $('.messageBox');
				// zapBox.html("MOUSE OUT");
				stopTimer();
				tip.hide();
			}
		);		   
		
		/* Delay the fade-in animation of the tooltip */
		setTimer = function() {
			$this.showTipTimer = setInterval("showTip()", defaults.delay);
		}
		
		stopTimer = function() {
			clearInterval($this.showTipTimer);
		}
		
		/* Position the tooltip relative to the class 
		   associated with the tooltip                */
		positionTip = function(top, left){
			var topOffset = tip.height();
			// var xTip = (left-30)+"px";
			// var yTip = (top-topOffset-60)+"px";
			var xTip = (left)+"px";
			var yTip = (top)+"px";
			tip.css({'top' : yTip, 'left' : xTip});
		}
		
		/* This function stops the timer and creates the
		   fade-in animation                          */
		showTip = function(){
			stopTimer();
			tip.animate({"top": "+=20px", "opacity": "toggle"}, defaults.speed);
		}
	});
};


