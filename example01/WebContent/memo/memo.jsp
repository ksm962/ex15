<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--  알집4개넣은 것을 라이브러리화--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<c:set var="path" value="${pageContext.request.contextPath }" /> 
<c:set var="url" value="${pageContext.request.requestURL }" />
<c:set var="uri" value="${pageContext.request.requestURI }" />

<script src="http://code.jquery.com/jquery-3.3.1.min.js">
</script>
    
    <script>
    $(document).ready(function(){
    	list();
		$("#btnSave").click(function(){
			GomemoProc();
		});
	});
    
    function GoChuga(){	
		var param = {}
		$.ajax({
			type:"post",
			data:param,
			url:"${path}/example01_servlet/memo.do",
			success:function(data){
			}
		})
	}
    
			function GomemoProc(){
			
					$.ajax({
						type:"post",
						data:$("#form").serialize(),
						url:"${path}/example01_servlet/memoProc.do",
						success:function(){ //콜백함수(서버에서 처리가 완료된 후 실행되는 코드)
							list();
							$("#name").val("");
							$("#memo").val("");
							suntaek_page('1');
						}
					});
				}
		
			function list(){
				var param={"pageNumber" : $("#span_pageNumber").text()}
				$.ajax({
					type:"post",
					data:param,
					url:"${path}/example01_servlet/list.do",
					success: function(data){
						$("#result").html(data);
					}
				});
			}
			
	</script>
	

    
    
    <form id ="form">
    	<table border="1" align="center" width="80%">
    		<tr>
    			<td colspan="2"><h1>메모장</h1></td>
    		</tr>
    		
    		<tr>
    			<td>이름</td>
    			<td><input type="text" name="name" id="name">
    		</tr>
    		
    		<tr>
    			<td>메모</td>
    			<td><input type="text" name="memo" id="memo">
    				<button type="button" id="btnSave">저장하기</button></td>
    		</tr>
    		
    		<tr>
    			<td colspan="10" align="center"><div id="result"></div></td>
    		</tr>
    	
		</table>    	
	</form> 	
 
 
    	
   

	
	
	
	
	
	