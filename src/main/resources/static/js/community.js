$("#postReplies").click(function () {
    $.ajax({
        type: "POST",
        url: "/comment",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": $("#question_id").val(),
            "content": $("#comment_content").val(),
            "type": 1
        }),
        success: function (data) {
            if (data.code == 200) {
                $("#comment_content").hide();
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