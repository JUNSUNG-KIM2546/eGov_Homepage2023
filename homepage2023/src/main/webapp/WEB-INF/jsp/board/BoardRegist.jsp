<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>BOARD 등록/수정 게시판</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<script src="https://cdn.tiny.cloud/1/4rq9k3tlzd5hh2wfb8qfvbln3u61yj5hx0zfimoycf4lsnha/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
<script>
$(function(){
    var plugins = [
        "advlist", "autolink", "lists", "link", "image", "charmap", "print", "preview", "anchor",
        "searchreplace", "visualblocks", "code", "fullscreen", "insertdatetime", "media", "table",
        "paste", "code", "help", "wordcount", "save"
    ];
    // 툴바
    var edit_toolbar = 'formatselect fontselect fontsizeselect |'
               + ' forecolor backcolor |'
               + ' bold italic underline strikethrough |'
               + ' alignjustify alignleft aligncenter alignright |'
               + ' bullist numlist |'
               + ' table tabledelete |'
               + ' link image';

    tinymce.init({
    	language: "ko_KR", //한글판으로 변경
        selector: '#boardCn',
        height: 500,
        menubar: false,
        plugins: plugins,
        toolbar: edit_toolbar,
        
        /*** image upload ***/
        image_title: true,
        /* enable automatic uploads of images represented by blob or data URIs*/
        automatic_uploads: true,
        /*
            URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
            images_upload_url: 'postAcceptor.php',
            here we add custom filepicker only to Image dialog
        */
        file_picker_types: 'image',
        /* and here's our custom image picker*/
        file_picker_callback: function (cb, value, meta) {
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');

            /*
            Note: In modern browsers input[type="file"] is functional without
            even adding it to the DOM, but that might not be the case in some older
            or quirky browsers like IE, so you might want to add it to the DOM
            just in case, and visually hide it. And do not forget do remove it
            once you do not need it anymore.
            */
            input.onchange = function () {
                var file = this.files[0];

                var reader = new FileReader();
                reader.onload = function () {
                    /*
                    Note: Now we need to register the blob in TinyMCEs image blob
                    registry. In the next release this part hopefully won't be
                    necessary, as we are looking to handle it internally.
                    */
                    var id = 'blobid' + (new Date()).getTime();
                    var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
                    var base64 = reader.result.split(',')[1];
                    var blobInfo = blobCache.create(id, file, base64);
                    blobCache.add(blobInfo);

                    /* call the callback and populate the Title field with the file name */
                    cb(blobInfo.blobUri(), { title: file.name });
                };
                reader.readAsDataURL(file);
            };
            input.click();
        },
        /*** image upload ***/
        
        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
    });
});
</script>
</head>
<body>

<%-- BBS Style --%>
<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
<%-- 공통 Style --%>
<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />

<c:choose>
	<c:when  test="${not empty searchVO.boardId}">
		<c:set var="actionUrl" value="/board/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/board/insert.do"/>
	</c:otherwise>
</c:choose>

<div class="container">
	<div id="contents">
		<form action="${actionUrl}" method="post" id="frm" name="frm" onsubmit="return regist()" enctype="multipart/form-data">	<!-- enctype이 있어야 첨부파일 올리수가 있다, 첨부파일은 무조건 post로 한다 -->
			<input type="hidden" name="boardId" value="${result.boardId}"/>
			
			<table class="chart2">
				<caption>게시글 작성</caption>
				<colgroup>
					<col style="width:120px"/>
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td>
							<input type="text" id="boardSj" name="boardSj" title="제목입력" class="q3" value="<c:out value="${result.boardSj}"/>"/>						
						</td>
					</tr>
					<tr>
						<th scope="row">공지여부</th>
						<td>
							<label for="noticeAtY">예 : </label>
							<input type="radio" id="noticeAtY" name="noticeAt" value="Y" <c:if test="${result.noticeAt eq 'Y'}">checked="checked"</c:if>/>
							&nbsp;&nbsp;&nbsp;
							<label for="noticeAtN">아니오 : </label>	
							<input type="radio" id="noticeAtN" name="noticeAt" value="N" <c:if test="${result.noticeAt ne 'Y'}">checked="checked"</c:if>/>
						</td>
					</tr>
					<tr>
						<th scope="row">비공개여부</th>
						<td>
							<label for="othbcAtY">예 : </label>
							<input type="radio" id="othbcAtY" name="othbcAt" value="Y" <c:if test="${result.othbcAt eq 'Y'}">checked="checked"</c:if>/>
							&nbsp;&nbsp;&nbsp;
							<label for="othbcAtN">아니오 : </label>	
							<input type="radio" id="othbcAtN" name="othbcAt" value="N" <c:if test="${result.othbcAt ne 'Y'}">checked="checked"</c:if>/>
						</td>
					</tr>
					<tr>
						<th scope="row">작성자ID</th>
						<td>
							<c:out value="${USER_INFO.id}"/>						
						</td>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td>
							<textarea id="boardCn" name="boardCn" rows="15" title="내용입력"><c:out value="${result.boardCn}"/></textarea>	<!-- 텍스트에어리어는 구분없이 붙여서 써야함 공백이 있으면 안됨 -->			
						</td>
					</tr>
					<tr>
						<th scope="row">파일첨부</th>
						<td>
							<input type="file" name="file_1"/><br>
							<input type="file" name="file_2"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn-cont ar">
				<c:choose>
					<c:when test="${not empty searchVO.boardId}">
						<c:url var="uptUrl" value="/board/update.do">
							<c:param name="boardId" value="${result.boardId}"/>
						</c:url>
						<a href="${uptUrl}" id="btn-reg" class="btn">수정</a>
						
						<c:url var="delUrl" value="/board/delete.do">
							<c:param name="boardId" value="${result.boardId}"/>
						</c:url>
						<a href="${delUrl}" id="btn-del" class="btn"><i class="ico-del"></i>삭제</a>					
					</c:when>
					<c:otherwise>
						<a href="#none" id="btn-reg" class="btn spot">등록</a>
					</c:otherwise>
				</c:choose>
				<c:url var="listUrl" value="/board/selectList.do"/>
				<a href="${listUrl}" class="btn">취소</a>
			</div>
		</form>
	</div>
</div>

<script> /* 아이디를 불러오는 = # , 클래스를 불러오는 = . */
$(document).ready(function(){
	//게시글 등록
	$("#btn-reg").click(function(){
		$("#frm").submit();
		return false;
	});
	
	//게시글 삭제
	$("#btn-del").click(function(){
		if(!confirm("삭제하시겠습니까?")){
			return false;
		}
	});
});

function regist(){
	if(!$("#boardSj").val()){
		alert("제목을 입력해주세요.");
		$("#boardSj").focus();
		return false;
	}
}
</script>
	
</body>
</html>
