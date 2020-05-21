<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
	<body>
		<div>
			Hello ${name}
		</div>
		<p style="background-color: red">
			${err}
		</p>
		
		<form id="form1" method="post" action="/list_value">
			<label>
				Operand 1:
			</label>
			<input type="number" id="operand11" name="operand1" required />			
			<br>
			<br>
			
			<label>
				Operand 2:
			</label>
			<input type="number" id="operand22" name="operand2" required />
			<br>
			<br>
			
			<label>
				Operator:
			</label>
			<input type="radio" name="operator" value="add" />+
			<input type="radio" name="operator" value="sub" />-
			<input type="radio" name="operator" value="mul" />*
			<input type="radio" name="operator" value="div" />/
			<br>
			<br>
			
			<input type="hidden" name="name" value="${name}" />
			<input type="hidden" id="thisField" name="inputName" value="1" />
			<input type="hidden" type='button' id="submitForm" value='Submit form' onClick='submitDetailsForm()'/>
			<input type='button' id="testForm" value='Test form'/>
			<br>
			<br>			
		</form>
		
		<div id="result">
			<c:if test="${list.size() > 0}">
				<table id ="tb01" style="border: 1px solid black">
					<th style="border: 1px solid black">
						Name
					</th>
					<th style="border: 1px solid black">
						Operation
					</th>
					<th style="border: 1px solid black">
						Result
					</th>
					<c:forEach items="${list}" var="cal">
						<tr>
							<td style="border: 1px solid black">
								${cal.name}
							</td>
							<td style="border: 1px solid black">
								${cal.x} ${cal.o} ${cal.y}
							</td>
							<td style="border: 1px solid black">
								${cal.result}
							</td>
						<tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		
	</body>
	
	<script  src="http://code.jquery.com/jquery-1.11.0.min.js">
	</script>
	
	<script>
		var inputName = document.getElementById("thisField").value;
		var operand1 = document.getElementsByName("operand1").value;
		var operand2 = document.getElementsByName("operand2").value;
		var b=document.getElementById("testForm");
				
		function submitDetailsForm() {
			$("#form1").submit();
		}
		
		$( document ).ready(function(){
			document.getElementById("thisField").value = "1";
			
			if($('#operand11').val() != '' && $('#operand22').val() != '' ) {
				$('#testForm').prop('disabled', false);
			} else{
				$('#testForm').prop('disabled', true);
			}
			
			$('#operand11').keyup(function() {
				$('#operand22').keyup(function() {
					if($('#operand11').val() != '' && $('#operand22').val() != '' ) {
						$('#testForm').prop('disabled', false);
				    } else {
				    	$('#testForm').prop('disabled', true);
				    }
				});
			});
					
			jQuery('#testForm').on('click', function(){
			    jQuery('#submitForm').trigger('click');
			});					
				
		 	$(window).focus(function(e) {
		 		document.getElementById("thisField").value = "0";
		 		jQuery('#submitForm').trigger('click');
			});			
		});		
	</script>	
</html>