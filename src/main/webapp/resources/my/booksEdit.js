
function booksEdit() {

    var delToken = {};
    delToken[tokenParam] = token;

    $("#books").jqGrid('navGrid', '#pagerBooks',
        {edit: true, add: true, del: true},
        //edit options
        {
            url: '/crud/editBook',
            beforeSubmit: function (postData, id) {
                postData[tokenParam] = token;
                console.log(postData);
                return [true, '']
            },
            closeAfterEdit: true,
            reloadAfterSubmit: false,
        },
        //add options
        {
            url: '/crud/addBook',
            beforeSubmit: function (postData, id) {
                postData[tokenParam] = token;
                console.log(postData);
                return [true, '']
            },
            closeAfterAdd: true,
            reloadAfterSubmit: false,
        },
        //delete options
        {
            url: '/crud/deleteBook',
            delData: delToken,
            closeAfterDelete: true,
            reloadAfterSubmit: false,
        });

    $("#books").navButtonAdd('#pagerBooks',
        {
            caption: "Take book",
            buttonicon: "ui-icon-note",
            onClickButton: takeBook,
            position: "last",
            title: "Take book",
            cursor: "pointer"
        }
    );
    $("#books").navButtonAdd('#pagerBooks',
        {
            caption: "Return book",
            buttonicon: "ui-icon-note",
            onClickButton: returnBook,
            position: "last",
            title: "Return book",
            cursor: "pointer"
        }
    );

    function takeBook() {
        var row = $("#books").jqGrid('getGridParam', 'selrow');

        if (row != null) {
            var opt = {
                autoOpen: false,
                modal: true,
                width: 400,
                height: 250,
                title: 'Take book',
                buttons: {
                    "Take book": take,
                    Cancel: function () {
                        dialogTake.dialog("close");

                    }
                },
                close: function () {

                    $("#takeBookError").css('display', 'none');
                }
            };
            dialogTake = $("#takeBookDialog").dialog(opt).dialog('open');
            dialogTake.dialog("close");
            dialogTake = $("#takeBookDialog").dialog(opt).dialog('open');
        }
        else $("#dialogSelectRow").dialog();
    }

    function take() {
        var row = $("#books").jqGrid('getGridParam', 'selrow');
        var data = $("#books").jqGrid('getRowData', row);

        var fName = $("#fName");
        var lName = $("#lName");
        $.ajax({
            type: "POST",
            url: "/events/takeBook",
            data: 'book_id=' + data.BOOK_ID +
            '&fName=' + fName.val() +
            '&lName=' + lName.val() + '&' + tokenParam + '=' + token,
            dataType: "html",
            success: function (msg) {
                dialogTake.dialog("close");
                $("#success").dialog();
                reload();
            },
            error: function (msg) {
                console.log(msg);
                $("#takeBookError").css('display', 'block');
                $("#takeBookError").html("Error" + msg.status + ". " +
                    "Is there " + fName.val() + ' ' + lName.val() + " in DB?");
            }

        });
    }

    function returnBook() {
        var row = $("#books").jqGrid('getGridParam', 'selrow');

        if (row != null) {
            var opt = {
                autoOpen: false,
                modal: true,
                width: 400,
                height: 250,
                title: 'Return book',
                buttons: {
                    "Return book": function () {
                        var row = $("#books").jqGrid('getGridParam', 'selrow');
                        var data = $("#books").jqGrid('getRowData', row);

                        $.ajax({
                            type: "POST",
                            url: "/events/returnBook",
                            data: 'book_id=' + data.BOOK_ID
                            + '&' + tokenParam + '=' + token,
                            dataType: "html",
                            success: function (msg) {
                                dialogReturn.dialog("close");
                                $("#success").dialog();
                                reload();
                            }
                        })
                    },
                    Cancel: function () {
                        dialogReturn.dialog("close");
                    }
                }
            };
            dialogReturn = $("#returnBookDialog").dialog(opt).dialog('open');
        }
        else $("#dialogSelectRow").dialog();
    }

    function reload() {
        $("#books").setGridParam({datatype: 'json'}).trigger("reloadGrid");
    }

}