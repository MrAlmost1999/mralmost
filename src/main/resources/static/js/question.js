/*回复按钮点击事件*/
$("#postReplies").click(function () {
    var question_id = $("#question_id").val();
    var comment_content = $("#comment_content").val();
    commentTarget(question_id, 1, comment_content);
});

$(".second-comment .btn-comment").click(function () {
    var commentId = $(this).attr("data-id");
    var content = $("#input-" + commentId).val();
    commentTarget(commentId, 2, content);
});

function commentTarget(targetId, type, content) {
    if (!targetId.trim()) {
        alert("系统出问题啦,请刷新页面或者稍后再试试吧~~");
        return;
    }
    if (!content.trim()) {
        alert("评论的内容不能为空!");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
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
}

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

/*展开二级回复*/
$(".glyphicon-comment").click(function () {
    var commentId = $(this).attr("data-id");
    var comment = $("#comment-" + commentId);
    if (!$(".collapse").hasClass("in")) {
        $.getJSON("/comment/" + commentId, function (data) {
            // var commentBody = $("comment-body-" + commentId);
            // var items = [];
            //
            // $.each(data, function (comment) {
            //     var c = $("<div/>", {
            //         "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12",
            //         html: comment.content
            //     });
            //     items.push(c);
            // });
            //
            // $("<div/>", {
            //     "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse second-comment",
            //     "id": "comment-" + commentId,
            //     html: items.join("")
            // }).appendTo(commentBody);

        });


        // //最外层div
        // var secondComment = $("<div></div>").addClass("col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse second-comment").attr("id", comment);
        // //里层同级上面的上面的div
        // var top = $("<div></div>").addClass("col-lg-12 col-md-12 col-sm-12 col-xs-12");
        // var topNext = $("<div></div>").addClass("media commentInfo");
        // var mediaLeft = $("<div></div>").addClass("media-left").append("<a></a>").attr("href", "#").append("<img>").addClass("media-object img-rounded avatar-img").attr("src", "图片地址").attr("alt", "头像");
        // var mediaBody = $("<div></div>").addClass("media-body").append("<span></span>").append("<a></a>").attr("href", "#").attr("class", "media-heading username").append("用户名");
        // // var deleteBtn =
    }
    comment.toggleClass("in");
    $(this).toggleClass("active");
});