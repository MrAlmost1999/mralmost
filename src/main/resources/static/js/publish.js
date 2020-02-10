/*回复按钮点击事件*/
$("#publish").click(function () {
    var id = $("#id").val();
    var title = $("#title").val();
    var description = $("#description").val();
    var tag = $("#tag").val();
    if (id == null || id == "") {
        alert("未知错误,请稍后重试!");
        return;
    }
    if (title == null || title == "") {
        alert("问题标题不能为空,请重新输入");
        $("#description").text(description);
        $("#tag").text(tag);
        return;
    }
    if (description == null || description == "") {
        alert("问题描述不能为空,请重新输入!");
        $("#title").text(title);
        $("#tag").text(tag);
        return;
    }
    if (tag == null || tag == "") {
        alert("问题标签不能为空,请重新输入!");
        $("#title").text(title);
        $("#description").text(description);
        return;
    }
    $.ajax({
        type: "POST",
        url: "/publish",
        data: $("#publishQuestion").serialize()
    });
});