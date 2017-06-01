function validateForm(event, formname) {
	
	 event.preventDefault();
 
	 if(confirm('Voulez vous effectuer cette action ?')){
		 document.formname.submit();
	 }
	
   }