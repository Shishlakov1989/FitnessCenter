<#include "security.ftlh">

<div class="navbar">
    <table class="menutab">
        <tr>
            <td width="15%"></td>
            <td width="70%">
                <#if isLogged>
                    <a href="/">Главная</a>
                    <a href="/customer/add">Добавить посетителя</a>
                    <a href="/customer">Список клиентов</a>
                    <#if isAdmin>
                        <a href="/user">Список пользователей</a>
                        <a href="/user/add">Добавить пользователя</a>
                    </#if>
                <#else>
                    <a href="/init">Создать первого пользователя</a>
                </#if>
            </td>
            <td width="15%">
                <#if isLogged>
                    ${name}
                    <form action="/logout" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit">Выйти</button>
                    </form>
                </#if>
            </td>
        </tr>
    </table>
</div>