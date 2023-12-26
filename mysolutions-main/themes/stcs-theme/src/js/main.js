/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

/*
 * This function gets loaded when all the HTML, not including the portlets, is
 * loaded.
 */
AUI().ready(
    function () {
     
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
          
          $('.resetbyEmail').on('click', function () {
            $('.forgot1').addClass('d-none');
            $('.forgot3').removeClass('d-none');
            $('.method1').addClass('d-none');
            $('.method3').removeClass('d-none');
          });
          
          $('.resetbyPhone').on('click', function () {
            $('.forgot2').addClass('d-none');
            $('.forgot3').removeClass('d-none');
            $('.method2').addClass('d-none');
            $('.method3').removeClass('d-none');
          });
          
          $('.byEmail').on('click', function () {
            $('.forgot1').addClass('d-none');
            $('.forgot2').removeClass('d-none');
            $('.method1').addClass('d-none');
            $('.method2').removeClass('d-none');
          });
          
          $('.byPhone').on('click', function () {
            $('.forgot2').addClass('d-none');
            $('.forgot1').removeClass('d-none');
            $('.method2').addClass('d-none');
            $('.method1').removeClass('d-none');
          });
          
          
          
          $('#four-tab').on('click', function () {
            $(this).find('.red-dot').addClass('d-none');
          });
          
          $('#five-tab').on('click', function () {
            $(this).find('.red-dot').addClass('d-none');
          });

    });

/*
 * This function gets loaded after each and every portlet on the page.
 *
 * portletId: the current portlet's id
 * node: the Alloy Node object of the current portlet
 */
Liferay.Portlet.ready(function (_portletId, _node) {});

/*
 * This function gets loaded when everything, including the portlets, is on
 * the page.
 */
Liferay.on('allPortletsReady', function () {});
