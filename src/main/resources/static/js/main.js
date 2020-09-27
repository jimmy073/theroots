$(window).ready(function(){
		$('eBtn').on('click', function(event){
		event.preventDefault();
		$('.myForm #saveA').modal();
	});
});