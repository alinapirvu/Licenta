require(['jquery'], function ($) {
 var Launcher = $('#back')
 Launcher.click(function(){
  var sPageURL = window.location.search.substring(1);
 // var sURLVariables = sPageURL.split('&');
  var sParameterName = sPageURL[0].split('=');
  var x = sParameterName[1];
  window.location.href = "http://localhost:8080/xwiki/bin/view/XWiki/Calendar/Events?eventDate=" + x;
  return false;
 });
});