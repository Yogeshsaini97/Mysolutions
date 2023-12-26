$("ul.navbar-nav li.dropdown").hover(function () {
  $(this).find(".dropdown-menu").stop(true, true).delay(100).fadeIn(300);
}, function () {
  $(this).find(".dropdown-menu").stop(true, true).delay(100).fadeOut(300);
});

$('.showHideDetails').on('click', function () {
  $(this).parents('tr').next('tr.milestoneDetails').toggleClass('d-none');
  $(this).find('img.angle-down').toggleClass('d-none');
  $(this).find('img.angle-up').toggleClass('d-none');
});

$(function () {
  $('[data-toggle="popover"]').popover()
})

// ******************************
  $("#show_hide_password i").on('click', function(event) {
      event.preventDefault();
      if($('#show_hide_password input').attr("type") == "text"){
          $('#show_hide_password input').attr('type', 'password');
          $('#show_hide_password i').addClass( "fa-eye-slash" );
          $('#show_hide_password i').removeClass( "fa-eye" );
      }else if($('#show_hide_password input').attr("type") == "password"){
          $('#show_hide_password input').attr('type', 'text');
          $('#show_hide_password i').removeClass( "fa-eye-slash" );
          $('#show_hide_password i').addClass( "fa-eye" );
      }
  });
// ******************************

$('.proceed').on('click', function () {
  $('.signup1').addClass('d-none');
  $('.signup2').removeClass('d-none');
});

$('.resetEmail').on('click', function () {
  $('.forgot1').addClass('d-none');
  $('.forgot2').removeClass('d-none');
});

$('.resetPhone').on('click', function () {
  $('.forgot2').addClass('d-none');
  $('.forgot3').removeClass('d-none');
});



$('#four-tab').on('click', function () {
  $(this).find('.red-dot').addClass('d-none');
});

$('#five-tab').on('click', function () {
  $(this).find('.red-dot').addClass('d-none');
});