<%@	attribute name="id" required="true"	%>
<%@	attribute name="val" required="false"	%>
<input	type="text" id="${id}" name="${id}" value="${val}"/>
<script>
	$("#${id}").datepicker({dateFormat:'dd/mm/yy'});
</script>