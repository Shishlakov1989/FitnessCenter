<#include "blocks/security.ftlh">
<#import "blocks/page.ftlh" as p>

<@p.page "Директор">
    <h4>Заполните данные о директоре</h4>
    <form action="/init" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <table>
            <tr>
                <td><label>Фамилия</label></td>
                <td>
                    <input type="text" name="lastName" <#if lastName??>value="${lastName}"</#if> tabindex="1"
                           autofocus/>
                    <#if lastNameError??>
                        <div class="error">
                            ${lastNameError}
                        </div>
                    </#if>
                </td>
                <td><label>Номер телефона</label></td>
                <td>
                    <input type="text" name="phoneNumber" placeholder="+79012345678"
                           <#if phoneNumber??>value="${phoneNumber}"</#if> tabindex="6"/>
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
                    <input type="text" name="firstName" <#if firstName??>value="${firstName}"</#if> tabindex="2"/>
                    <#if firstNameError??>
                        <div class="error">
                            ${firstNameError}
                        </div>
                    </#if>
                </td>
                <td><label>Должность</label></td>
                <td>
                    <input type="text" name="position" <#if position??>value="${position}"</#if> tabindex="7"/>
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
                    <input type="text" name="middleName" <#if middleName??>value="${middleName}"</#if> tabindex="3"/>
                    <#if middleNameError??>
                        <div class="error">
                            ${middleNameError}
                        </div>
                    </#if>
                </td>
                <td><label>Имя пользователя</label></td>
                <td>
                    <input type="text" name="username" <#if username??>value="${username}"</#if> tabindex="8"/>
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
                    <input type="date" name="birthday" <#if birthday??>value="${birthday}"</#if> tabindex="4"/>
                    <#if birthdayError??>
                        <div class="error">
                            ${birthdayError}
                        </div>
                    </#if>
                </td>
                <td><label>Задайте пароль</label></td>
                <td>
                    <input type="password" name="password" <#if password??>value="${password}"</#if> tabindex="9"/>
                    <#if passwordError??>
                        <div class="error">
                            ${passwordError}
                        </div>
                    </#if>
                </td>
            </tr>
            <tr>
                <td><label>Пол</label></td>
                <td><select name="sex" tabindex="5">
                    <option value="m" selected>м</option>
                    <option value="f">ж</option>
                </select></td>
                <td><label>Дата начала работы</label></td>
                <td>
                    <input type="date" name="startWork" <#if startWork??>value="${startWork}"</#if> tabindex="10"/>
                    <#if startWorkError??>
                        <div class="error">
                            ${startWorkError}
                        </div>
                    </#if>

                </td>
            </tr>
        </table>
        <button type="submit" tabindex="11">Записать</button>
    </form>
</@p.page>