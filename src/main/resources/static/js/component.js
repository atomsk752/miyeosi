// <![CDATA[
jQuery(function($){

	$('.radio').each(function(index){
		var txt = $(this).text();
		$(this).html('');
		$(this).prepend('<span class="et-radio">&nbsp;</span>');
		$(this).append('<span class="et-radio_txt">'+txt+'</span>');

		if($(this).attr('data-on') == 'on'){
			$(this).addClass('on');
			$('input[name="'+$(this).attr('data-name')+'"]').val($(this).attr('data-val'));
		}else{
			$(this).attr('data-on','off');
		}
	});

	$(document).on('click','.radio',function(){
		var _val = $(this).attr('data-val');
		var _input_name = $(this).attr('data-name');
		var _this = $('input[name="'+_input_name+'"]');
		var _option = $(this).attr('data-option');
		$(this).addClass('on');
		$(this).attr('data-on','on');

		if(_option != 'link'){
			_this.val(_val);

			$('.radio[data-name="'+_input_name+'"]').each(function(index){
				if($(this).attr('data-val') != _val){
					$(this).attr('data-on','off');
					$(this).removeClass('on');
				}
			});
		}else{
			location.replace(_val);
		}
	});

	$('.select').each(function(index){
		var _this = $(this).attr('name');
		var _parent = $(this).parent();
		var _width = $(this).attr('data-width');
		var _default_width = '';
		var _height = $(this).attr('data-height');
		var _margin = $(this).attr('data-margin');
		var child_margin = 30-parseInt(_height);
		if(child_margin == null || child_margin == 'NaN' || _height == null){
			child_margin = 2;
		}else{
			child_margin = parseInt(child_margin);
		}
		if(child_margin < 0){
			child_margin = child_margin*child_margin;
		}

		if(_margin != '0'){
			var margin_add = 'margin-top:'+(parseInt(_margin)+parseInt(_height))+'px;';
		}else{
			var margin_add = 'margin-top:'+_height+'px;';
		}

		if(child_margin < 3){
			var child_margin = '3';
		}

		if(_width != ''){
			_default_width = 'width:'+_width+'px;';
		}
		

		if(_height != ''){
			_default_height = 'height:'+_height+'px;line-height:'+_height+'px;';
		}

		_parent.prepend('<div class="select-box" data-name="'+_this+'"><div class="clear"></div><div class="select-child" data-name="'+_this+'" style="'+margin_add+'display:none;"></div></div>');
		_sel_txt = '';
		$('option',this).each(function(s_index){
			var _value = $(this).val();
			var _txt = $(this).text();
			var _sel = $(this).attr('selected');
			if(_sel == 'selected'){
				var _add_txt = ' on';
				_sel_txt = _txt;
			}else{
				var _add_txt = '';
				if((_sel_txt == null || _sel_txt == '') && s_index == '0'){
					_sel_txt = _txt;
				}
			}
			$('.select-child[data-name="'+_this+'"]').append('<div class="select-option'+_add_txt+'" data-value="'+_value+'" data-name="'+_this+'">'+_txt+'</div>');
		});
		var _parent_width = $('.select-child[data-name="'+_this+'"]').width();
		$('.select-box[data-name="'+_this+'"]').prepend('<div class="select-parent" style="min-width:'+_parent_width+'px;'+_default_width+_default_height+'" data-margin="margin-top:-'+child_margin+'px;" data-name="'+_this+'" data-on="off"><div class="select-value" style="'+_default_height+'">'+_sel_txt+'</div><div class="clear"></div></div>');
		//_sel_txt = '';
		var prev_val = '';
		$('.select-option').each(function(index){
			if(prev_val == $(this).attr('data-value')){
				$(this).remove();
			}else{
				prev_val = $(this).attr('data-value');
			}
			_this_name = $(this).parent().attr('data-name');
			_this_width = parseInt($('.select-parent[data-name="'+_this_name+'"]').width())+29;
			_this_width = parseInt(_this_width)+2;

			_this_height = parseInt($('.select-parent[data-name="'+_this_name+'"]').height());

			if(margin_add != 'margin-top:-2px;'){
				var height_add = 'height:'+_this_height+'px;';
			}

			if($(this).text() == _sel_txt){
				$(this).addClass('on');
			}
				$(this).attr('style','width:'+_this_width+'px;height:'+_this_height+'px;');
				$(this).attr('data-margin','margin-top:-'+child_margin+'px;');
		});
		$('.select-child').hide();

		$(this).hide();
	});

	$(document).on('click','.select-parent',function(){
		var _this = $(this).attr('data-name');
		var _on = $(this).attr('data-on');
		$('.select-box').each(function(){
			if($(this).attr('data-name') != _this){
				$(this).attr('style','z-index:1;');
			}else{
				$(this).attr('style','z-index:2;');
			}
		});
		if(_on == 'off'){
			$('.select-parent').attr('data-on','off');
			$(this).attr('data-on','on');
			$('.select-child[data-name="'+_this+'"]').show();
		}else{
			$('.select-parent').attr('data-on','off');
			$(this).attr('data-on','off');
			$('.select-child[data-name="'+_this+'"]').hide();
		}
		$('.select-child[data-name="'+_this+'"]').attr('style',$(this).attr('style')+$(this).attr('data-margin'));
	});

	$(document).on('click','.select-option',function(){
		var _this = $(this).attr('data-name');
		var _this_val = $(this).attr('data-value');
		var _this_txt = $(this).text();
		$('.select-parent').attr('data-on','off');
		$('.select-option[data-name="'+_this+'"]').removeClass('on');
		$(this).addClass('on');
		//$('select[name="'+_this+'"]').val(_this_val);
		$('select[name="'+_this+'"]').val(_this_val).change();
		//$('select[name="'+_this+'"] option[value="'+_this_val+'"]').prop('selected',true);
		$('.select-parent[data-name="'+_this+'"] .select-value').html(_this_txt);
		$('.select-option').each(function(){
			if($(this).attr('class') != 'select-option on'){
				$(this).attr('style',$(this).attr('style')+$(this).attr('data-margin'));
			}else{
				var reg_txt = $(this).attr('data-margin');
				$(this).attr('style',$(this).attr('style').replace(reg_txt,''));
			}
		});
		$(this).parent().hide();
	});

	$(document).mouseup(function(e){
		$('.select-child').each(function(index){
			if(($(this).is(e.target) && $(this).has(e.target).length === 0)){
				$('.select-parent').attr('data-on','off');
			}else{
				$(this).hide();
			}
		});
	});
});

function common_msg(msg,delay){
	if(delay == null){
		delay = 1000;
	}
	var html ='<div class="msg_box">';
			html += msg;
		html += '</div>';

	$('body').append(html);
	console.log($('.msg_box').width());
	$('.msg_box').attr('style','margin-left:-'+($('.msg_box').width()/2)+'px;');
	$('.msg_box').fadeIn(function(){
		setTimeout(function(){
			$('.msg_box').fadeOut(function(){
				$('.msg_box').remove();
			});
		},delay);
	});
}
// ]]>