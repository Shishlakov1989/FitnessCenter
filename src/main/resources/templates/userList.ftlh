<#import "blocks/page.ftlh" as p>

<@p.page "Список сотрудников">
    <div class="filter">
        <form method="get" action="/user">
            <input type="text" name="filter" <#if filter??>value="${filter}"</#if>>
            <button class="btn" type="submit">Найти</button>
        </form>
    </div>
    <div>
        <table>
            <#list userList as us>
                <tr>
                    <td>${us.id}</td>
                    <td>${us.employee.getFio()}</td>
                    <td><#if us.employee.birthday??>${us.employee.getBirthdayString()}<#else>нет данных</#if></td>
                    <td><a href="/user/edit/${us.id?c}">Подробнее</a></td>
                </tr>
            </#list>
        </table>
    </div>
</@p.page>