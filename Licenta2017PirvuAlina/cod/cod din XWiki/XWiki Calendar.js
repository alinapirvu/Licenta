require(['jquery'], function ($) {
 var Launcher = $('#addEvent')
 //Launcher.hide();
 Launcher.click(function(){
  $('#calendar').hide();
  Launcher.hide();
  $('#back').show();
  $('#back').click(function(){
  location.reload();
  });
  $('#pop').show();
  //document.getElementById('pop').style.display = "block";
 
 });

 $('#formAddEvent').click(function() {
  document.getElementById('pop').style.display = "block";
  var $name = $('#name').val();
  var $d = $('#date').val();
  var $date = $d.substring(0, 10);
  console.log($d);
  console.log($date);
  var $time = $d.substring(11, 16);
  console.log($time);
  var $place = $('#place').val();
  var $infor = $('#infor').val();
  var $people = $('#people').val();
  var url = "http://localhost:8080/xwiki/bin/view/XWiki/Calendar/Event/CreateEvent?name=" + $name + "&date=" + $date + "&time=" + $time + "&place=" + $place + "&info=" + $infor + "&people=" + $people;
  console.log(url);
  document.location.href = url;
  return false;
  
});
 
});