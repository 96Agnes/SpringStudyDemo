function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType : 'application/json',
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (response) {
            if(response.code == 200){
                window.location.reload();
                //$("#comment_section").hide();
            }else{
                if(response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=928889ecd34ed6d66e85&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable",true);
                    }
                }else{
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}