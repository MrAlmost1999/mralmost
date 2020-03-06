/*发布按钮点击事件:一级回复*/
$("#postReplies").click(function () {
    var question_id = $("#question_id").val();
    var comment_content = $("#comment_content").val();
    commentTarget(question_id, 1, comment_content);
});

/*回复按钮点击事件:二级回复*/
$(".second-comment .btn-comment").click(function () {
    var commentId = $(this).attr("data-id");
    var content = $("#input-" + commentId).val();
    if (!content) {
        return;
    }
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
            "type": type,
            "content": content
        }),
        success: function (data) {
            if (data.code == 200) {
                location.reload();
            } else {
                if (data.code == 2003) {
                    var flag = confirm(data.message);
                    if (flag) {
                        window.open("/login");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(data.message);
                }
            }
        }
    });
}

/*展开二级回复*/
$(".open-second-comment").click(function () {
    var commentId = $(this).attr("data-id");
    var comment = $("#comment-" + commentId);
    if (!$(".collapse").hasClass("in")) {
        $.getJSON("/comment/" + commentId, function (data) {
            if (data.data == null) {
                return;
            } else {
                var subCommentContainer = $("#comment-" + commentId);
                if (subCommentContainer.children().length != 1) {
                } else {
                    $.each(data.data.reverse(), function (index, comment) {
                        if (comment.user.avatar=="default-avatar.png"){
                            comment.user.avatar="/images/default-avatar.png";
                        }
                        var mediaLeftElement = $("<div/>", {
                            "class": "media-left"
                        }).append($("<img/>", {
                            "class": "media-object img-rounded avatar-img",
                            "src": comment.user.avatar
                        }));

                        var mediaBodyElement = $("<div/>", {
                            "class": "media-body second-comment-username"
                        }).append($("<a/>", {
                            "html": comment.user.username,
                            "href": "#",
                            "class": "media-heading"
                        }));
                        var userId = $("#user-id").val();
                        if (userId != null) {
                            if (userId == comment.commentator) {
                                var deleteBodyElement = $("<span/>", {
                                    "class": "pull-right btn-delete-comment icon",
                                    "data-id": comment.id,
                                }).append("<img src='/images/delete-btn-18x18.png'/>", {});
                                mediaBodyElement.append(deleteBodyElement);
                            }
                        }
                        var mediaContent = $("<div/>", {
                            "html": comment.content
                        }).append($("<div/>", {
                            "class": "menu"
                        }).append($("<span/>", {
                            "class": "pull-right community-menu",
                            "html": comment.gmtCreate
                        })));
                        mediaBodyElement.append(mediaContent);

                        //三级回复标签
                        /*.append($("<span/>", {
                            "class": "glyphicon glyphicon-comment three-comment",
                            "id": comment.user.name + "and" + comment.id
                        }))*/


                        var mediaElement = $("<div/>", {
                            "class": "media commentInfo"
                        }).append(mediaLeftElement).append(mediaBodyElement);

                        var commentElement = $("<div/>", {
                            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12"
                        }).append(mediaElement);

                        subCommentContainer.prepend(commentElement);
                    });
                }
            }
        });
    }
    comment.toggleClass("in");
    $(this).toggleClass("active");
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
                "commentId": $(this).attr("data-id")
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

/*三级回复点击事件*/
/*$(document).on("click", ".three-comment", function () {
    var userInfo = $(this).attr("id");
    var split = userInfo.split("and");
    var username = split[0];
    var id = split[1];
    $(".comment-input").attr("value", "@" + username);

    var id = $(this).attr("id");
    $.ajax({
        url: "/comment",
        type: "POST",
        data: JSON.stringify({
            "parentId": id,
            "content": $("")
        }),
        success: function (data) {
            alert(data.message);
        }
    });
});*/

