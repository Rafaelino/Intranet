$(document).ready(function() {
     $(function() {
         $("#search").autocomplete({     
             source : function(request, response) {
               $.ajax({
                    url : "searchcontroller",
                    type : "GET",
                    data : {
                           term : request.term
                    },
                    dataType : "json",
                    success : function(data) {
                          response(data);
                    }
             });
          },
          select: function( event, ui ) {
              window.location.replace("http://evernet:8080/test/myaccompt?name="+ui.item.value);
              return false;
           }
      });
   });
});