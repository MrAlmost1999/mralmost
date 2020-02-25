$(".delete-btn").click(function () {
    if (!confirm("确认删除这条问题吗?")) {
        return;
    }
    $.ajax({
        url: "/delete",
        type: "DELETE",
        data: {
            id: $(this).attr("data-id")
        },
        success: function () {
            location.reload();
        }
    });
});