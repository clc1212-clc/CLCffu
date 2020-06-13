(function($){
	$.fn.Selector = function (options) {
		var opt = $.extend({
			container: $(this)
		}, options || {});
		var container = opt.container,
			select = container.find("select"),
			options = select.children(),
			optLength = options.length,
			containerWidth = container.width(),
			containerHeight = container.height();
		var data = [];
		for(var i = 0; i < optLength; i++) {
			var obj = {};
			obj.name = options[i].innerHTML;
			obj.value = options[i].value;
			data.push(obj);
		}
		var curOption = $("<span>");
		curOption.html(data[0].name);
		curOption.addClass("current-selector");
		curOption.css({
			zIndex: 1,
			fontSize:18+"px",
			position: "absolute",
			left:0,
			top:0,
			bottom:0,
			right:0,
			textAlign: "left",
			paddingLeft:15+"px",
			lineHeight: containerHeight+"px",
			display: "block"
		});
		container.append(curOption);
		var selectorList = $("<ul>");
		selectorList.addClass("selector-ul");
		var listText = "";
		for(var j = 0; j < data.length; j++) {
			listText += "<li class='option-list' data-val='"+data[j].value+"'>"+data[j].name+"</li>";
		}
		selectorList.html(listText);
		selectorList.css({
			width: containerWidth+"px",
			position: "absolute",
			top:containerHeight+"px",
			left: 0,
			background:"#DCDCDC"
		})
		selectorList.find("li").css({
			padding: "10px",
			borderBottom:"1px solid #666"
		});
		container.append(selectorList);
		selectorList.slideUp(0);
		container.on("click",curOption,function(){
			if($(this).hasClass("showed")){
				$(this).removeClass("showed");
				selectorList.slideUp(100);
			}else{
				$(this).addClass("showed");
				selectorList.slideDown(100);
			}
		});
		container.on("click",".option-list",function(e){
			var curIndex = $(this).index();
			options.eq(curIndex).attr("selected",true);
			curOption.html($(this).text());
		});
	}
	$(".selector-container").Selector();
})(jQuery)
