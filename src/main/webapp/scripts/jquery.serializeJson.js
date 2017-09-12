/**
 * @author 吴金华
 * @version 1.0
 * @date 2015年6月12日 功能：将一个表单的元素的数据封装成一个json格式的数据
 */
(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		$.each( serializeObj, function(name, value){
			if ($.isArray(value)) {
				serializeObj[name] = value.join(",");
			}
		});
		return serializeObj;
	};
})(jQuery);