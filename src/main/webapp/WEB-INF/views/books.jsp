<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
    //securety tokens for server submit
    var token = '${_csrf.token}';
    var tokenParam = '${_csrf.parameterName}';
</script>

<script type="text/javascript" src="/resources/my/books.js"></script>

<sec:authorize access="isAuthenticated() and principal.username=='admin'">
    <script type="text/javascript" src="/resources/my/booksEdit.js"></script>
    <div>Admin mode</div>
</sec:authorize>

<div>
    <table id="books">
        <tr>
            <td></td>
        </tr>
    </table>
</div>
<div id="pagerBooks"></div>

<div id="takeBookDialog" class="ui-jqdialog-content ui-widget-content" style="display:none;">
    <div>
        <form name="FormPost" id="FrmGrid_books" class="FormGrid" onsubmit="return false;"
              style="width:100%;overflow:auto;position:relative;height:100%;">

            <div class="FormError ui-state-error" id="takeBookError" style="display:none;"></div>
            <div class="tinfo topinfo"></div>

            <table id="TblGrid_books" class="EditTable" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td class="CaptionTD">User's first name</td>
                    <td class="DataTD">&nbsp;<input class="FormElement ui-widget-content ui-corner-all"
                                                    role="textbox" name="fName" id="fName" type="text">
                    </td>
                </tr>
                <tr>
                    <td class="CaptionTD">User's last name</td>
                    <td class="DataTD">&nbsp;<input class="FormElement ui-widget-content ui-corner-all"
                                                    role="textbox" name="lName" id="lName" type="text">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>

<div id="returnBookDialog" class="ui-jqdialog-content ui-widget-content" style="display:none;">
    <div>
        Do you really want to return this book?
    </div>
</div>

<div id="dialogSelectRow" title="Warning" style="display:none">
    <p>Please select row</p>
</div>

<div id="success" title="Success" style="display:none">
    <p>Updated successfully</p>
</div>

