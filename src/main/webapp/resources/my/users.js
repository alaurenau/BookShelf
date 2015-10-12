function grid() {
    $("#users").jqGrid({
        url: '/getUsers',
        datatype: 'json',
        mtype: 'GET',
        colNames: ['First Name', 'Last Name', 'Have book', 'Author'],
        colModel: [
            {name: 'FIRST_NAME', index: 'FIRST_NAME', width: 300},
            {name: 'LAST_NAME', index: 'LAST_NAME', width: 300},
            {name: 'TITLE', index: 'TITLE', width: 300},
            {name: 'AUTHOR', index: 'AUTHOR', width: 300}

        ],
        jsonReader: {
            root: "contactData",
            page: "currentPage",
            total: "totalPages",
            records: "totalRecords",
            repeatitems: false,
            id: "id"
        },
        pager: '#pagerUsers',
        rowNum: 25,
        loadonce: true,
        rowList: [20, 30, 50],
        sortname: 'bookId',
        sortorder: 'asc',
        viewrecords: true,
        gridview: true,
        height: 600,
        width: 1024,
        caption: 'Users'

    });
}