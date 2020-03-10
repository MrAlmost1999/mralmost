$(function () {
    $("input[type='password'][data-eye]").each(function (i) {
        let $this = $(this);

        $this.wrap($("<div/>", {
            style: 'position:relative'
        }));
        $this.css({
            paddingRight: 60
        });
        $this.after($("<div/>", {
            html: 'Show',
            class: 'btn btn-primary btn-sm',
            id: 'passeye-toggle-' + i,
            style: 'position:absolute;right:10px;top:50%;transform:translate(0,-50%);padding: 2px 7px;font-size:12px;cursor:pointer;'
        }));
        $this.after($("<input/>", {
            type: 'hidden',
            id: 'passeye-' + i
        }));
        $this.on("keyup paste", function () {
            $("#passeye-" + i).val($(this).val());
        });
        $("#passeye-toggle-" + i).on("click", function () {
            if ($this.hasClass("show")) {
                $this.attr('type', 'password');
                $this.removeClass("show");
                $(this).removeClass("btn-outline-primary");
            } else {
                $this.attr('type', 'text');
                $this.val($("#passeye-" + i).val());
                $this.addClass("show");
                $(this).addClass("btn-outline-primary");
            }
        });
    });
});

/*登录按钮点击事件*/
$("#btn-login").click(function () {
    $.ajax({
        url: "/login",
        type: "POST",
        data: {
            "remember": $("#remember").is(":checked"),
            "username": $("#username").val(),
            "password": $("#password").val()
        },
        success: function (data) {
            if (data.code == 200) {
                location.href = "/";
            } else {
                alert(data.message);
            }
        }
    });
});

/*注册按钮点击事件*/
$("#btn-register").click(function () {
    //用户名正则:6-16位,包含字母,数字,下划线
    var userReg = /^[a-zA-Z0-9_]{6,16}$/g;
    //密码正则:以字母开头,长度在6~18之间,只能包含字母、数字和下划线
    var passReg = /^[a-zA-Z]\w{5,17}$/g;
    //邮箱正则
    var emailReg = /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
    //用户输入的账号,密码和邮箱
    var username = $("#username").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var agreement = $("#agreement").is(":checked")
    if (!userReg.test(username)) {
        alert("您输入的账号格式不正确,账号格式应为6-16位,包含字母,数字,下划线~");
        return;
    }
    if (!passReg.test(password)) {
        alert("您输入的密码格式不正确,密码格式应为以字母开头,长度在6~18之间,只能包含字母、数字和下划线~");
        return;
    }
    if (!emailReg.test(email)) {
        alert("您输入的邮箱格式不正确,请重新输入~");
        return;
    }
    if (!agreement) {
        alert("同意《MrAlmost使用协议》后才能注册哦~");
    }
    $.ajax({
        url: "/exist_user",
        type: "GET",
        data: {
            "username": $("#username").val(),
        },
        success: function (data) {
            if (data.code == 200) {
                $.ajax({
                    url: "/sendmail",
                    type: "POST",
                    data: {
                        "username": username,
                        "password": password,
                        "email": email,
                        "agreement": agreement
                    },
                    success: function () {
                        location.href = "/email";
                    }
                });
            } else {
                alert(data.message);
            }
        }
    });
});

/*重置密码按钮点击事件*/
$(".btn-reset-password").click(function () {
    //密码正则:以字母开头,长度在6~18之间,只能包含字母、数字和下划线
    var passReg = /^[a-zA-Z]\w{5,17}$/g;
    var password = $("#new-password").val();
    if (!passReg.test(password)) {
        alert("您输入的密码格式不正确,密码格式应为以字母开头,长度在6~18之间,只能包含字母、数字和下划线~");
        return;
    }
    $.ajax({
        url: "/password",
        type: "POST",
        data: {
            "newPassword": password
        },
        success: function (data) {
            if (data.code == 200) {
                alert("修改成功,请重新登录!");
                location.href = "/login";
            } else {
                alert(data.message);
                location.href = "/password";
            }
        }
    });
});