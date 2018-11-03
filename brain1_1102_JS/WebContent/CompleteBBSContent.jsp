<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style>
        p {
            text-align: center;
        }

        .top {
            background-color: rgba(66, 133, 244, 1.0);
            padding: 1.2%;
            max-width: 100%;
            margin: -8px;
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
            text-align: left;
            font: bold 135% '나눔고딕';
            color: #111c46;
            margin-bottom: 0;
            padding: 1%;
            margin-left: 10%;
        }

        #conten {
            margin-left: 0;
            width: 70%;
            margin-top: 0%;
        }

            #conten > tbody > tr > th {
                text-align: left;
                border-bottom: 1px solid #d8d8d8;
                font: normal 80% '맑은 고딕';
                padding: 1%;
                color: gray;
            }

        h2 {
            font: normal 200% '나눔고딕';
            color: black;
            padding: 0%;
        }

        #conten > tbody > tr > td {
            padding: 3%;
            font: normal 90% '맑은 고딕';
            color: black;
            border-bottom: 1px solid #d8d8d8;
        }



        .comment {
            padding-top: 0px;
            margin-left: 225px;
            clear: both;
        }

        h5 {
            font: bold 120% '나눔 고딕';
            border-bottom: 1px solid #111c46;
            padding: 0.7%;
            border-bottom-width: 2px;
            color: #111c46;
            overflow: hidden;
            width: 11.2%;
            text-transform: uppercase;
        }

        .button {
            margin-top: 1%;
            background-color: rgba(66, 133, 244, 1.0);
            color: white;
            border:none;

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
        }

        .table {
            background-color: rgba(66, 133, 244, 1.0);
            color: white;
            text-decoration: none;
            font: normal 90% '맑은 고딕';
            padding: 0.5% 1%;
            float: right;
            margin-left: 1%;
        }

            .table:hover {
                font-weight: bold;
            }

        .write {
            text-decoration: none;
            font: normal 90% '맑은 고딕';
            color: black;
            float: left;
            margin-right: 2%;
        }

            .write:hover {
                font-weight: bold;
                border-bottom: 1px solid black;
            }
    </style>
    
    <link type="text/css" rel="stylesheet" href="copyright_css.css" />
    <link type="text/css" rel="stylesheet" href="nav_css.css" />
    <meta charset="utf-8" />
    <title>TO-DO-LIST</title>
</head>
<body>
    <div style="min-width : 1200px;">
            <a class="mypage_logout" href="/Gaagle/logout" style="float:right; margin-right:2%; padding:0.2%;">로그아웃</a>
    </div>
    <p>
        <img src="Logo.png" style="max-width:25%" /></a>
    </p>
    <div>
        <hr style="color:darkblue;width:90%;" />
        
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
            	<td onclick="location.href = 'expiredBBSView'">Expired-list</td>
            </tr>
        </table>
        
        <table id="conten">
          <tr>
             <th><h2>${completeInfo.title}</h2></th>
             <th><h2>${completeInfo.date}</h2></th>
          </tr>
            <tr>
                <td>${completeInfo.content}</td>
            </tr>
      	    <tr>
           		<td style="border:none">
          		     <a href="./completeBBSView" class="table" style="margin-right:-4%; padding:1% 2%" >목록</a>
                <a href="./completeBBSDelete?seqNo=${completeInfo.seqNo}" class="write">삭제</a>   
           		</td>
       		</tr>
   </table>
</body>
</html>