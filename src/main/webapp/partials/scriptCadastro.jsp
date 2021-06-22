<script type="text/javascript">
	const url = location.origin + "/usuarios"
	let list = []
	
	const addUser = (e) => {
	 e.preventDefault()
	 const data = new FormData(document.getElementById("form"))
	 const phones = createListObject(document.querySelectorAll(".numero").length)
		
	  if ((data.get("name") !== "") && (data.get("email") !== "") && (data.get("password") !== "")) {
		 $.ajax ({
		      "method": "POST",
		      "url": url,
		      "data": {
		    	"action":"add",
	            "name":data.get("name"), 
	          	"email":data.get("email"), 
	           	"password":data.get("password"),
	           	"phones":JSON.stringify(phones)
		      },
		      "success": function(resp) {
		    	 alert(resp)
		   	  }
	 	  }) 
	    } else {
	    	alert("Preencha todos os campos!")
	    }
	  }
</script>