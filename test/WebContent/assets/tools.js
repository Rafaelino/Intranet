function validateForm(event, formname) {
	
	 event.preventDefault();
 
	 if(confirm('Voulez vous effectuer cette action ?')){
		 document.suppression.submit();
	 }
	
   }

function validateFormDelete(event, formname) {
	
	 event.preventDefault();

	 if(confirm('Voulez vous effectuer cette action ?')){
		 document.suppression.submit();
	 }
	
  }