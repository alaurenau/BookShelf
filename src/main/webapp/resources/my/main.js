$(function () {
    checkURL();
    $('a').click(function (e) {
        checkURL(this.hash);
    });
    setInterval("checkURL()", 250);
});

var lasturl = "";

function checkURL(hash) {
    if (!hash) hash = window.location.hash;

    if (hash != lasturl) {
        lasturl = hash;
        loadPage(hash);
    }
}

function loadPage(url) {
    url = url.replace('#', '');
    $.ajax({
        type: "GET",
        url: "/getHtml",
        data: 'page=' + url,	//parameter
        dataType: "html",
        success: function (msg) {
            if (parseInt(msg) != 0) {
                $('#pageContent').html(msg);
                eval(grid());
            }
        }
    });
}