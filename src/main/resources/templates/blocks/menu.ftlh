<#include "security.ftlh">

<div class="navbar">
    <table class="menutab">
        <tr>
            <td width="15%"></td>
            <td width="70%">
                <ul>
                <#if isLogged>
                    <li><a href="/">Главная</a></li>
                    <li><a href="/customer/add">Добавить посетителя</a></li>
                    <li><a href="/customer">Список клиентов</a></li>
                    <#if isAdmin>
                        <li><a href="/user/add">Добавить пользователя</a></li>
                        <li><a href="/user">Список пользователей</a></li>
                    </#if>
                <#else>
                    <li><a href="/init">Создать первого пользователя</a></li>
                </#if>
                </ul>
            </td>
            <td width="15%">
                <#if isLogged>
                    <a href="/user/edit/${user.id}" id="username">${name}</a>
                    <form action="/logout" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btnlink" type="submit">Выйти</button>
                    </form>
                </#if>
            </td>
        </tr>
    </table>
</div>