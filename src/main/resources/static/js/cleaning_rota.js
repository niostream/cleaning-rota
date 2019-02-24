$(function(){
  $('#switchButton').on('click', function() {
	if($(this).hasClass('switch')) {
	  $('.showButton').show();
	  $(this).removeClass('switch')
	} else {
	  $('.showButton').hide();
	  $(this).addClass('switch')
	}
  })
})