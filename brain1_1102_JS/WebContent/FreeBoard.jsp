<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style>
        p {
            text-align: center;
        }

        .mypage_logout {
            color: white;
            font: normal 90% 'Century Gothic';
            text-decoration: none;
            padding: 1%;
        }

            .mypage_logout:hover {
                font-weight: bold;
            }

        h3 {
            text-align: center;
            font: normal 250% '나눔손글씨 펜';
            color: #111c46;
            margin-bottom: 0;
            text-shadow: 1px 1px 5px lightgray;
            margin-left: 110px;
        }

        h6 {
            text-align: center;
            font: normal 20px '주아';
            margin-top: 10px;
            margin-bottom: 0;
            margin-left: 110px;
        }

        table.board {
            margin-left: 10px;
            border-bottom: 1px solid #dcd9d9;
            border-top: 2px solid black;
        }

            table.board > tbody > tr > th {
                font: bold 85% '맑은 고딕';
                color: #333232;
                padding: 1%;
                border-bottom: 1px solid darkgray;
            }

            table.board > tbody > tr > td {
                font: normal 80% '맑은 고딕';
                color: black;
                text-align: center;
                padding: 0.8%;
                border-bottom: 1px solid #dcd9d9;
            }

        p.next {
            clear: both;
            margin-top: 470px;
            margin-left: 100px;
        }

            p.next > a {
                font: bold 70% '맑은 고딕';
                color: #111c46;
                text-align: right;
                padding: 1%;
                text-decoration: none;
                padding: 3%;
            }

                p.next > a:hover {
                    text-decoration: underline;
                }

        #nav {
            margin-left: 30px;
            margin-right: 30px;
        }

            #nav > tbody > tr > th {
                background-color: rgba(66, 133, 244, 1.0);
                font: 500 20px '주아';
                color: white;
                box-shadow: 0 0 5px grey;
                padding: 7px 0;
            }

            #nav > tbody > tr > td {
                background-color: white;
                cursor: pointer;
                text-align: left;
                padding-left: 10px;
                box-shadow: 0 0 5px grey;
                font: normal 15px '주아';
                border-top: 5px white;
                padding-top: 7px;
                padding-bottom: 7px;
            }

                #nav > tbody > tr > td:hover {
                    background-color: rgba(66, 133, 244, 1.0);
                    color: white;
                    transition-property: background-color,color;
                    transition-duration: 0.5s;
                }

        table {
            float: left;
            margin-top: 20px;
        }

        .write {
            text-decoration: none;
            font: normal 80% '맑은 고딕';
            color: black;
            float: right;
            margin-right: 100px;
            clear : both;
        }

            .write:hover {
                font-weight: bold;
                border-bottom: 1px solid black;
            }

        a {
            color: black;
            text-decoration: none;
        }

            a:hover {
                color: gray;
            }
    </style>
    <script type="text/javascript">
    	function writeForm(){
    		location.href="BoardWriteForm.bo";
    	}
    </script>
    <link type="text/css" rel="stylesheet" href="copyright_css.css" />
    <link type="text/css" rel="stylesheet" href="nav_css.css" />
    <meta charset="utf-8" />
    <title>TO-DO-LIST</title>
</head>
<body>
    <div style="min-width:1200px;">
        <p>
          <img src="Logo.png" style="max-width:25%" /></a>
		</p>
        <nav>
        </nav>
        <h3>To do list</h3>
        <table id="nav" cellspacing="7">
            <tr>
                <th width="130px">메  뉴</th>
            </tr>
            <tr>
                <td onclick="location.href = 'bbs-list'">To-do-list</td>
            </tr>
            <tr>
            	<td onclick="location.href = 'completeBBSView'">Complete-list</td>
            </tr>
             <tr>
             	<c:if test="${flag}">
             		<td onclick="location.href = 'expiredBBSView'">Expired-list <a style="color:red">[N]</a></td>
             	</c:if>
             	<c:if test="${not flag}">
             		<td onclick="location.href = 'expiredBBSView'">Expired-list</td>
             	</c:if>
            </tr>
        </table>
        <table class="board">
            <tr>
                <th width="80px">번호</th>
                <th width="450px">제목</th>
                <th width="300px">마감기한</th>
                <th width="80px">완료</th>
            </tr>
           <c:forEach var="cnt" begin="1" end="${BBS_LIST.listSize}">
					<tr>
						<td>${cnt}</td>
						<td><a href='Q&AFree.jsp?SEQ_NO=${BBS_LIST.seqNo[cnt-1]}'>${BBS_LIST.title[cnt-1]}</a></td>
						<td>${BBS_LIST.date[cnt-1]}</a></td>
						<td><button type="button" 
							onclick="window.location.href='./completeBBSAdd?seqNo=${BBS_LIST.seqNo[cnt-1]}'">완료</button></td>
					</tr>
				</c:forEach>
        </table><br />
		<a href="FreeWrite.html" class="write" style="margin-top:2%;">글쓰기</a>
        <p class="next">
        	<c:if test="${!BBS_LIST.lastPage}">
        		<a href="bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize - 1]}">NEXT</a>
        	</c:if>
        </p>
    </div>
</body>
</html>