<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--  알집4개넣은 것을 라이브러리화--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<c:set var="path" value="${pageContext.request.contextPath }" /> 
<c:set var="url" value="${pageContext.request.requestURL }" />
<c:set var="uri" value="${pageContext.request.requestURI }" />


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="http://code.jquery.com/jquery-3.3.1.min.js">
</script>
  pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>  
	<table border="1">
		<tr>
			<th>id</th>
			<th>이름</th>
			<th>메모</th>
			<th>날짜</th>
		</tr> 
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${jj}</td>	
				<td>${dto.name }</td>
				<td>${dto.memo }</td>
				<td>${dto.wdate}</td>
			</tr>
			<c:set var="jj" value="${jj=jj-1 }"></c:set>
				</c:forEach>
				
	
	<tr>
		<td colspan="7" height="50" align="center">
		 <a href="#" onclick="suntaek_page('1');">[첫페이지]</a>
         &nbsp;&nbsp;
      <c:if test="${startPage >blockSize }">
         <a href="#" onclick="suntaek_page('${startPage-blockSize}');">[이전 10개]</a>         
        </c:if>
        <c:if test="${startPage <=blockSize }">
         [이전 10개]         
         </c:if>
            &nbsp;
         <c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
            <c:if test="${i == pageNumber }">
            [${i }]
            
            </c:if>
            <c:if test="${i!=pageNumber }">
            <a href="#" onclick="suntaek_page('${i}');">${i }</a>
            </c:if>
            </c:forEach>
            &nbsp;
            <c:if test="${lastPage < totalPage }">
               <a href="#" onclick="suntaek_page('${startPage + blockSize}');">[다음 10개]</a>
            </c:if>
            <c:if test="${lastPage >= totalPage }">
               [다음 10개]
            </c:if>
         &nbsp;&nbsp;
      
         <a href="#" onclick="suntaek_page('${totalPage}');">[끝페이지]</a>         
		</td>
	</tr>
 	
 	<tr>
  		<td colspan="15" height="50" align="right">
  		
  	</tr>
 </table>
 
 <script>
 function suntaek_page(value1){
		$("#span_pageNumber").text(value1);
		list();
	}
</script>  
 		
 


