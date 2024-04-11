<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>列表页面</title>
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<link href="css/table.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div style="margin: 0 20px;">
			<div class="cztable">
			    <div class="query">
                	<form action="/keep-servlet-news/newsquery" method="post">
                	    搜索条件:&nbsp;&nbsp;
                        <input type="text" name="keyword" value="" />
                		<input type="submit" value="查询" class="btn-primary" />
                	</form>
                </div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr style="height: 25px" align="center">
							<th>编号</th>
							<th>标题</th>
							<th>来源</th>
							<th>创建时间</th>
						</tr>
						<c:forEach items="${requestScope.newsList }" var="news" varStatus="status">
							<tr align="center">
								<td>${status.count}</td>
								<td>
									<a target="_blank" href="/keep-servlet-news/newsdetails?url=${news.url}">
										${news.title}
									</a>
								</td>
								<td>${news.source}</td>
								<td>
									<fmt:formatDate value="${news.ctime}" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
                	<input type="checkbox" id="robotchk"
                		<c:if test="${requestScope.robot }">checked</c:if>
                			onclick="robotNews()" />定时获取新闻
                </div>
			</div>
		</div>
	</body>
</html>
<script>
	let robot = document.querySelector("#robotchk");
	if (robot.checked) {
		id = setTimeout(function() {
			location.replace("/keep-servlet-news/newsrobot?robot=true")
		}, 10000)
	}
	function robotNews(){
		if (robot.checked) {
			id = setTimeout(function() {
				location.replace("/keep-servlet-news/newsrobot?robot=true")
			}, 10000)
		}else{
			location.replace("/keep-servlet-news/newsrobot?robot=false")
		}
	}
</script>


