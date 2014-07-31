$(document).ready(function(){
			document.getElementById('form-edit').style.display="block";
			document.getElementById('form-senha').style.display="none";
			document.getElementById('form-delete').style.display="none";

			document.getElementById('li1').className="active";
			
			var num = 0;

	$("#edit").click(
		function(){
			document.getElementById('form-edit').style.display="block";
			document.getElementById('form-senha').style.display="none";
			document.getElementById('form-delete').style.display="none";

			document.getElementById('li1').className="active";
			document.getElementById('li2').className="";
			document.getElementById('li3').className="";
	});

	$("#senha").click(
		function(){
			document.getElementById('form-edit').style.display="none";
			document.getElementById('form-senha').style.display="block";
			document.getElementById('form-delete').style.display="none";		
			
			document.getElementById('li1').className="";
			document.getElementById('li2').className="active";
			document.getElementById('li3').className="";
	});
	

	$("#delete").click(
		function(){
			document.getElementById('form-edit').style.display="none";
			document.getElementById('form-senha').style.display="none";
			document.getElementById('form-delete').style.display="block";

			document.getElementById('li1').className="";
			document.getElementById('li2').className="";
			document.getElementById('li3').className="active";

			num = Math.floor((Math.random() * (999999-1+1))+1);
			document.getElementById('codigo').innerText = num; 
	});

	$("#confCodigo").keyup(function(){
		
		var confCode = $('#confCodigo').val();
		if (num == confCode) {
			$('#bDelete').removeAttr('disabled');
		}else {
			$('#bDelete').attr('disabled','disabled');
		}
	});
	
	$("#confSenha").keyup(function(){
		var novaSenha = $('#novaSenha').val();
		var confSenha = $('#confSenha').val();
		if (num == confCode) {
			$('#bSenha').removeAttr('disabled');
		}else {
			$('#bSenha').attr('disabled','disabled');
		}
	});
	
	
	
});