<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
    var token = '&${_csrf.parameterName}=${_csrf.token}';
</script>
<script type="text/javascript" src="/resources/my/login.js"></script>

<sec:authorize access="!isAuthenticated()">
<form name='loginForm' action="/login" method="POST">
    <table>
        <tr>
            <td>User:</td>
            <td><input type="text" id="username" name="username"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" id="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan='2'>
                Remember me:
                <input id="remember_me" name="remember-me" type="checkbox"/>
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input type="button" onclick="login()" value="Login"/>
            </td>
        </tr>
    </table>
    <input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <sec:authorize access="principal.username=='admin'">
        <div>Admin mode</div>
    </sec:authorize>

<form action="/logout" method="POST" id="logoutForm">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="button" onclick="logout()"  value="Logout"/>
</form>
</sec:authorize>