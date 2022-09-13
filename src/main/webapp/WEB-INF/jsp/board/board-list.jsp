<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script defer src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script defer src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container px-2 px-lg-1 mt-5">
    <a href="/member/logout" class="btn btn-primary">로그아웃</a>
    <h1>게시판 목록</h1>
    <div>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th class="col-1">번호</th>
                <th class="col-1">제목</th>
                <th class="col-2">작성자</th>
                <th class="col-1">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr style="cursor: pointer"
                    onclick="location.href='/board/detail?boardNo=${board.boardNo}'">
                    <th>
                            ${board.boardNo}
                    </th>
                    <th>
                            ${board.boardTitle}
                    </th>
                    <th>
                            ${board.boardContent}
                    </th>
                    <th>
                            ${board.boardDate}
                    </th>
                </tr>
            </c:forEach>
            <div class="d-flex flex-row-reverse">
                <a href="/board/insert-form" class="btn btn-primary">글입력</a>
            </div>
            </tbody>
        </table>
    </div>
    <%-- 페이지네이션 --%>
    <ul class="pagination justify-content-center my-2 mb-2">
        <%-- 이전 --%>
        <c:if test="${pageNation.startPage ne 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageNation.path}?current=${pageNation.startPage-1}">
                    이전 </a>
            </li>
        </c:if>
        <%-- 페이지넘버 --%>
        <c:forEach begin="${pageNation.startPage}" end="${pageNation.endPage}" varStatus="status">
            <c:if test="${pageNation.currentPage eq status.index}">
                <li class="page-item active">
                    <a class="page-link">${status.index}
                    </a>
                </li>
            </c:if>
            <c:if test="${pageNation.currentPage ne status.index}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageNation.path}?current=${status.index}">${status.index}
                    </a>
                </li>
            </c:if>
        </c:forEach>
        <%-- 다음버튼 --%>
        <c:if test="${pageNation.endPage ne pageNation.lastPage}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageNation.path}?current=${pageNation.endPage+1}">다음</a>
            </li>
        </c:if>
    </ul>
</div>
<c:if test="${check eq 'insert'}">
    <script>alert('등록성공');</script>
</c:if>
</body>
</html>
