<#import "blocks/page.ftlh" as p>

<@p.page "Страница сотрудника">
    <h4>Редактирование пользователя #${user.id?c}</h4>
    <form action="/user/edit/${user.id?c}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <table class="info" border="1">
            <tr>
                <td colspan="2">Имя пользователя</td>
                <td colspan="2">
                    <input type="text" name="username" value="${user.username}" disabled>
                </td>
            </tr>
            <tr>
                <td>Фамилия</td>
                <td>
                    <input type="text" name="lastName" value="${user.employee.lastName?ifExists}">
                </td>
                <td>Должность</td>
                <td>
                    <input type="text" name="position" value="${user.employee.position?ifExists}">
                </td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>
                    <input type="text" name="firstName" value="${user.employee.firstName?ifExists}">
                </td>
                <td>Телефон</td>
                <td>
                    <input type="text" name="phoneNumber" value="${user.employee.phoneNumber?ifExists}">
                </td>
            </tr>
            <tr>
                <td>Отчество</td>
                <td>
                    <input type="text" name="middleName" value="${user.employee.middleName?ifExists}">
                </td>
                <td>Дата начала работы</td>
                <td>
                    <input type="date" name="startWork" value="${user.employee.startWork?ifExists}">
                </td>
            </tr>
            <tr>
                <td>Дата рождения</td>
                <td>
                    <input type="date" name="birthday" value="${user.employee.birthday?ifExists}">
                </td>
                <td>Администратор</td>
                <td><input type="checkbox" name="admin" <#if user.isAdmin()>checked</#if>></td>
            </tr>
            <tr>
                <td>Пол</td>
                <td>${user.employee.getSexString()}</td>
                <td>Статус</td>
                <td><#if user.employee.active>Активный<#else>Неактивный</#if></td>
            </tr>

        </table>
        <button type="submit">Изменить</button>
    </form>
    <form action="/user/fire/${user.id?c}" method="get">
        <button type="submit">Уволить</button>
    </form>
</@p.page>