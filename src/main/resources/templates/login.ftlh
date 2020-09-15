<#import "blocks/page.ftlh" as p>

<@p.page "Авторизация">
<div class="middle">
    <form action="/login" method="post">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Войти</button>
    </form>
</div>
</@p.page>