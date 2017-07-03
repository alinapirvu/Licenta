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

 var $oldName = $('#name').val();
  var $d = $('#date').val();
  var $oldDate = $d.substring(0, 10);
  console.log("d: " + $d);
  console.log("olddate" + $oldDate);
  var $oldTime = $d.substring(11, 16);

 $('#editEvent').click(function() {
  document.getElementById('pop').style.display = "block";
  var $name = $('#name').val();
  var $d = $('#date').val();
  var $date = $d.substring(0, 10);
  console.log("d2: " + $d);
  console.log("newdate: " + $date);
  var $time = $d.substring(11, 16);
  console.log("newtime: " + $time);
  if($('#place').val() == ""){
      var $place = "";
  } else {
      var $place = $('#place').val();
  }
  if($('#infor').val() == ""){
      var $infor = "";
  } else {
  var $infor = $('#infor').val();
  }
  if($('#people').val() == ""){
      var $people = "";
  } else {
  var $people = $('#people').val();
  }
  var url = "http://localhost:8080/xwiki/bin/view/XWiki/Calendar/Edit?oldName=" + $oldName + "&oldDate=" + $oldDate + "&oldTime=" + $oldTime + "&name=" + $name + "&date=" + $date + "&time=" + $time + "&place=" + $place + "&info=" + $infor + "&people=" + $people;
  console.log(url);
  document.location.href = url;
  return false;
  
});
});