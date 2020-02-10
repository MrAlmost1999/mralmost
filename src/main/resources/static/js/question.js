/*回复按钮点击事件*/
$("#postReplies").click(function () {
    var question_id = $("#question_id").val();
    var comment_content = $("#comment_content").val();
    if (!question_id.trim()) {
        alert("系统出问题啦,请刷新页面或者稍后再试试吧~~");
        return;
    }
    if (!comment_content.trim()) {
        alert("评论的内容不能为空!");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": question_id,
            "content": comment_content,
            "type": 1
        }),
        success: function (data) {
            if (data.code == 200) {
                location.reload();
            } else {
                if (data.code == 2003) {
                    var flag = confirm(data.message);
                    if (flag) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.fc94755b2e36e7af&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(data.message);
                }
            }
        }
    });
});

/*删除按钮点击事件*/
$(document).on("click", ".btn-delete-comment", function () {
    var c = confirm("确认删除吗?");
    if (c) {
        $.ajax({
            url: "/comment",
            type: "DELETE",
            dataType: "json",
            data: {
                "commentId": $(this).val()
            },
            success: function (data) {
                alert("删除成功");
                location.reload();
            }
        });
    } else {
        return;
    }
});

