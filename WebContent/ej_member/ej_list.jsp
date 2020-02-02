<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> 
<!-- jQuery -->
<style>
@font-face{
   font-family: '10X10';
   src: url("http://localhost:9080/projectvinyl/font/10X10_1.woff");
   
}

/* header nav 전문가찾기 글씨색상  */
  .hs_header_nav > a:nth-child(1){
    color: rgb(0, 153, 255);
  }


#ej_orderbutton{
width:140px;
}

#ej_searchbox{border:1px solid silver;
width: 100%;
padding: 20px;
margin: 12px auto;
/* backgroud-color: solid silver !important; */
}
.ej_searchbox1{
position:relative;
	left:290px;
    width: 44%;	
    background-color:white;
    border: 1px solid #757575;
    border-radius: 5px;
    font-family:10X10;
} 
.ej_txtsearch{
width:72%;
border: 1px transparent;}

.ej_searchop{
font-family:"10X10";
font-weight:bolder;
	width :24%;
	border: 1px transparent;
	padding: 5px;
	border-radius: 5px;
	position:relative;
	right:5px;
}
/* .ej_stxtsearch1{
border-radius:5px;
}
.ej_stxtsearch2{
border-radius:5px;
} */
#ej_search{
width:29px;
height:29px;
margin:8px;
position:relative;
top:13px;
/* border: 1px solid silver; */
}

.ej_selectbox{
font-family:"10X10";
width:150px;
margin:6px;
border-radius: 5px;
}
#ej_line{border:0.5px solid silver;}
.ej_all{
margin: auto;
text-align: center;
}
ul li{
list-style:none;
}
tr{
margin:auto;
text-align: center;
}
table{
/* width:80%; */
font-family:"10X10";

margin: auto;
text-align: center;}
.ej_page{
margin: auto;
text-align: center;
}
.ej_userimage{
width:50px;
height:50px;}

/*////////////////////////////////////////////////  */
.ej_form-control{
width:100px;
/* background-color:silver; */
border: 1px transparent;
font-size: 20px;
}
/* .ej_selectbox{
width:130px;
font-size: 20px;
background-color: rgb(220, 234, 248);
} */
.ej_control-label{
font-size: 20px 0px 0px 0px;}
#ej_check{
position:relative;
top:5px;
width:30px; height:30px;}
.modal-body{
padding:20px 0px 0px 0px;
}
.ej_modalfooter{
padding: 0px 0px 15px 0px;}


</style>



<script>

function delconfirm(){

	var jbResult = confirm( '해당 회원을 삭제 하시겠습니까?' );
	//document.write( jbResult );	
}
$(document).ready(function(){
	 $('#exampleModal').on('show.bs.modal', function (event) {//보여지는 시점에서 이벤트 발생
		   console.log('rteeeets');
		  var button = $(event.relatedTarget); // Button that triggered the modal
		  var recipient = button.data('whatever'); // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  console.log('test');
		  var modal = $(this);
		 //modal.find('.modal-title').text('New message to ' + recipient)
		  modal.find('.modal-body input').val(recipient);
		  console.log(button);
		  console.log(modal+"!!!"); 
		});
	 
/* 	 $("#modal-body").modal('show').css({
		    'margin-top': function () { //vertical centering
		        return -($(this).height() / 2);
		    },
		    'margin-left': function () { //Horizontal centering
		        return -($(this).width() / 2);
		    }
		}); */

	
	
});

</script>



<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

 <div class="ej_all">
 <ul><li>
<img src="http://localhost:9080/projectvinyl/img/ej_user.png" alt="user"
 class="ej_userimage"><h3>회원조회</h3></li>
 </ul>
<form method="post" action="ej_list.do">
<!--setAttribute로 지정한것 변수로 받아오기  -->
<c:set var="list" value="${requestScope.list }"/>
<c:set var="currpage" value="${requestScope.currpage }"/>
<c:set var="startblock" value="${requestScope.startblock }"/>
<c:set var="endblock" value="${requestScope.endblock }"/>
<c:set var="totalpage" value="${requestScope.totalpage }"/>
<c:set var="search" value="${requestScope.search }"></c:set>
<c:set var="txtsearch" value="${requestScope.txtsearch }"></c:set> 


<div id="ej_searchbox">
<div class="ej_searchbox1">
<select name="search" class="ej_searchop"><!--search txtsearch받아야댐 ListAction에서  -->
	<option value="id">아이디</option>
	<option value="name">이름</option>
</select>
<input type="text" name="txtsearch"
class="ej_txtsearch" placeholder="회원 정보를 검색해보세요">
<!-- <input type="submit" value="검색"> -->
</div>
<br>
<div id="ej_line"></div>
<label for="point">구매액</label>
<input type="text" name="stxtsearch1">원~<input type="text" name="stxtsearch2">원
<!-- <input type="submit" value="상세검색">  -->
<input type="image" 
src="http://localhost:9080/projectvinyl/img/ej_search.png" alt="search"
id="ej_search">
</div>

</form>

<table class="table">
<thead class="thead-dark">
<tr><th scope="col">선택</th><th scope="col">회원번호</th>
<th scope="col">아이디</th><th scope="col">비밀번호</th>
<th scope="col">이름</th><th scope="col">구매액</th><th scope="col">적립금</th>
<th scope="col">삭제</th></tr>
</thead>
<tbody>
	<!--arraylist니까 foreach  -->
	<c:forEach var="item" items="${list}">
	<tr>
 	<td><input type="checkbox" name="select" id="select" value="${item.memNo }" ></td>
	<td><c:out value="${item.memNo }"></c:out></td> 
	<td>${item.id }</td>
	<td>${item.pwd }</td>
	<td>${item.name }</td>
	<td>${item.total }원</td>
	<td>
		<button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal"
	 data-whatever="${item.memNo }" id="ej_orderbutton">${item.point }포인트</button></td>
	<td> 
	<a class="btn btn-outline-secondary" href="ej_delete.do?memNo=${item.memNo}" onclick="delconfirm();">삭제</a>
	<%-- <a href="ej_delete.do?memNo=${item.memNo}" onclick="delconfirm();">삭제</a> --%></td>


	</tr>
	 </c:forEach>
	 
</tbody>
</table><br>

    <form method="post" action="ej_insert.do">
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header"> 
        <h4 class="modal-title" id="exampleModalLabel">적립금부여</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
  
          <div class="form-group">	
           <ul>
          <li>
            <label for="recipient-name" class="ej_control-label" >회원번호:</label>
            <input type="text" class="ej_form-control" id="recipient-name" name="what"></li></ul>
          </div>
       </div>
        
           <div class="ej_modalfooter">
           <ul><li>
            <input type="text" name="addpoint" id="addpoint"
            placeholder="포인트적립">
            <input type="image" 
src="http://localhost:9080/projectvinyl/ej_order/ej_check.png" alt="check"
id="ej_check">
            </li></ul></div>
  
    </div>
   
    </div>
  
   </div>
   </form>   
</div>  
  



<div class="ej_page">
<div class="btn-group" role="group" aria-label="...">
<c:forEach var="i" begin="${starblock }+1" end="${endblock }" step="1">
	<c:if test="${currpage==i }">
	 <button type="button" class="btn btn-default"
	  onclick="location.href='ej_list.do?curr=${i }&search=${search}&txtsearch=${txtsearch }'">${i }</button>
	<%-- <c:out value="${i }"/> --%>
	
	</c:if>

	<c:if test="${currpage!=i }">
	 <button type="button" class="btn btn-default"
	  onclick="location.href='ej_list.do?curr=${i }&search=${search}&txtsearch=${txtsearch }'">${i }</button>
	<%-- <a href="ej_list.do?curr=${i }&search=${search}&txtsearch=${txtsearch }">${i }</a> --%>
	</c:if>
	
</c:forEach><br>
</div>
</div>




</div><!-- div class=ej_all -->




</body>
</html>