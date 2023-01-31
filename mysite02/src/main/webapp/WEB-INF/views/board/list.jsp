<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/board?page=1"
					method="post">
					<input type="hidden" id="keyword" name="keyword" value="${keyword }">
					<input type="text" id="kwd" name="kwd" value="${keyword }"> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>





					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<c:if
							test="${count-5*(page-1) >=count-status.index&&count-5*(page)+1<=count-status.index}">
							<tr>
								<td>${count-status.index}</td>

								<td style="text-align:left; padding-left:${vo.depth *20}px">
									<c:if test="${vo.depth > 0 }">
										<img
											src="${pageContext.request.contextPath }/assets/images/reply.png">
									</c:if> <a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a>
								</td>


								<td>${vo.name}</td>
								<td>${vo.hit }</td>
								<td>${vo.reg_date }</td>



								<!-- 로그인 삭제 -->
								<c:if test="${vo.user_no == authUser.no }">
									<td><a
										href="${pageContext.request.contextPath }/board?a=deleteform&no=${vo.no }"
										class="del">삭제</a></td>
								</c:if>
						</c:if>
					</c:forEach>




					<!-- <c:set var="count" value="${fn:length(list) }" /> -->


				</table>

				<!-- pager 추가 -->

				<div class="pager">
					<ul>
						<c:if test="${page>1 }">
							<li><a
								href="${pageContext.request.contextPath }/board?page=${page-1 }&kwd=${keyword }">◀</a></li>
						</c:if>

						<c:forEach var="i" begin="1" end="${count/5+1 }" step="1">
							<li><a
								href="${pageContext.request.contextPath }/board?page=${i }&kwd=${keyword }">${i }</a></li>
						</c:forEach>

						<c:if test="${page < count/5 }">
							<li><a
								href="${pageContext.request.contextPath }/board?page=${page +1}&kwd=${keyword }">▶</a></li>
						</c:if>

					</ul>
				</div>


				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=write&no=-1"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>