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

/*标签点击事件*/
$(".tag").click(function () {
    var tag = $(this).attr("data-id");
    var previous = $("#tag").val();
    //判断标签框是否为空
    if (previous) {
        //不为空
        //判断标签框是否包含此次要加入的标签
        if (previous.split(",").indexOf(tag) == -1) {
            //不包含,追加逗号和标签
            $("#tag").val(previous + "," + tag);
        } else {
            //包含则删除这个标签
            var split = previous.split(",");
            $.each(split, function (index, item) {
                if (item == tag) {
                    //splice(索引,删除数量)
                    split.splice(index, 1);
                }
            });
            //然后将删除完的内容重新赋值上去
            $("#tag").val(split);
        }
    } else {
        //为空则直接加标题
        $("#tag").val(tag);
    }
});

/*标签输入框获得焦点的事件*/
$("#tag").focus(function () {
    $("#publish-select-tag").show();
});
