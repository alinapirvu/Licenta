require(['jquery'], function ($) {
 var Launcher = $('#back')
 Launcher.click(function(){
  window.location.href = "http://localhost:8080/xwiki/bin/view/XWiki/XWiki%20Calendar";
  return false;
 });
});