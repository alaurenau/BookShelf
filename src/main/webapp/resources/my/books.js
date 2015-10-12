function grid() {

    $("#books").jqGrid({
        url: '/getBooks',
        datatype: 'json',
        mtype: 'GET',
        colNames: ['Id', 'Title', 'Author', 'lastName', 'Currently used by', 'In use since'],
        colModel: [
            {name: 'BOOK_ID', index: 'BOOK_ID', width: 100, key: true, sorttype: 'int'},
            {
                name: 'TITLE', index: 'TITLE', width: 300, editable: true,
                editrules: {
                    required: true
                }
            },
            {
                name: 'AUTHOR', index: 'AUTHOR', width: 300, editable: true,
                editrules: {
                    required: true
                }
            },
            {name: 'LAST_NAME', index: 'LAST_NAME', hidden: true},
            {
                name: 'FIRST_NAME',
                index: 'FIRST_NAME',
                width: 200,
                formatter: function (value, options, rowObject) {
                    if (value == null) {
                        return "-";
                    }
                    return value + ' ' + rowObject['LAST_NAME'];
                }
            },
            {
                name: 'USED_SINCE',
                index: 'USED_SINCE',
                formatter: 'date',
                formatoptions: {srcformat: 'U/1000', newformat: 'Y/m/d H:i:s'},
                width: 300
            }
        ],
        jsonReader: {
            root: "data",
            repeatitems: false,
            id: "BOOK_ID"
        },
        pager: '#pagerBooks',
        rowNum: 25,
        loadonce: true,
        rowList: [20, 30, 50],
        sortname: 'bookId',
        sortorder: 'asc',
        viewrecords: true,
        gridview: true,
        height: 600,
        width: 1024,
        caption: 'Books'
    });
        booksEdit();
}
