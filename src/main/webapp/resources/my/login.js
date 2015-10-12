
function login() {
    var user = $('#username').val();
    var pass = $('#password').val();

    if ($("#remember_me").prop('checked')) {

        var rem = '&remember-me=on';
    } else {
        var rem = '';
    }

    $.ajax({
        type: "POST",
        url: "/login",
        data: "username=" + user + "&password=" + pass
        + token + rem,
        success: function () {
            loadPage("#books");
        }
    });
}

function logout() {
    $.ajax({
        type: "POST",
        url: "/logout",
        data: token,
        success: function () {
            loadPage("#login");
        }
    });
}