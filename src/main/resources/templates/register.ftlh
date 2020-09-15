<#include "blocks/security.ftlh">
<#import "blocks/page.ftlh" as p>

<@p.page "Новый сотрудник">
    <h4>Регистрация сотрудника</h4>
    <form action="/user/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <table>
            <tr>
                <td><label>Фамилия</label></td>
                <td>
                    <input type="text" name="lastName" <#if lastName??>value="${lastName}"</#if>/>
                    <#if lastNameError??>
                        <div class="error">
                            ${lastNameError}
                        </div>
                    </#if>
                </td>
                <td><label>Номер телефона</label></td>
                <td>
                    <input type="text" name="phoneNumber" placeholder="+79012345678"
                           <#if phoneNumber??>value="${phoneNumber}"</#if>/>
                    <#if phoneNumberError??>
                        <div class="error">
                            ${phoneNumberError}
                        </div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td><label>Имя</label></td>
                <td>
                    <input type="text" name="firstName" <#if firstName??>value="${firstName}"</#if>/>
                    <#if firstNameError??>
                        <div class="error">
                            ${firstNameError}
                        </div>
                    </#if>
                </td>
                <td><label>Должность</label></td>
                <td>
                    <input type="text" name="position" <#if position??>value="${position}"</#if>/>
                    <#if positionError??>
                        <div class="error">
                            ${positionError}
                        </div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td><label>Отчество</label></td>
                <td>
                    <input type="text" name="middleName" <#if middleName??>value="${middleName}"</#if>/>
                    <#if middleNameError??>
                        <div class="error">
                            ${middleNameError}
                        </div>
                    </#if>
                </td>
                <td><label>Имя пользователя</label></td>
                <td>
                    <input type="text" name="username" <#if username??>value="${username}"</#if>/>
                    <#if usernameError??>
                        <div class="error">
                            ${usernameError}
                        </div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td><label>Дата рождения</label></td>
                <td>
                    <input type="date" name="birthday" <#if birthday??>value="${birthday}"</#if>/>
                    <#if birthdayError??>
                        <div class="error">
                            ${birthdayError}
                        </div>
                    </#if>
                </td>
                <td><label>Задайте пароль</label></td>
                <td>
                    <input type="password" name="password" <#if password??>value="${password}"</#if>/>
                    <#if passwordError??>
                        <div class="error">
                            ${passwordError}
                        </div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td><label>Пол</label></td>
                <td><select name="sex">
                    <option value="m" selected>м</option>
                    <option value="f">ж</option>
                </select></td>
                <td><label>Администратор</label></td>
                <td><input type="checkbox" name="admin"></td>
            </tr>
        </table>
        <button class="btn" type="submit">Добавить</button>
    </form>
</@p.page>