$(".logOut").click(function () {
    $.ajax({
        url: "/logOut",
        type: "GET",
        success: function (data) {
            location.reload();
        }
    });
});

