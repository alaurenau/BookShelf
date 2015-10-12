function grid() {

    $("#rating").jqGrid({
        url: '/getRatings',
        datatype: 'json',
        mtype: 'GET',
        colNames: ['Title', 'Author', 'Rating'],
        colModel: [
            {name: 'TITLE', index: 'TITLE', width: 300},
            {name: 'AUTHOR', index: 'AUTHOR', width: 300},
            {name: 'RATING', index: 'RATING', width: 300, sorttype:"int"}
        ],
        pager: '#pagerRating',
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