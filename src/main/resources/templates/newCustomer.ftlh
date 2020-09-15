<#import "blocks/page.ftlh" as p>

<@p.page "Новый посетитель">
    <h4>Регистрация клиента</h4>
    <form action="/customer/add" method="post" enctype="multipart/form-data">
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
                <td><label>Дата рождения</label></td>
                <td>
                    <input type="date" name="birthday" <#if birthday??>value="${birthday}"</#if>/>
                    <#if birthdayError??>
                        <div class="error">
                            ${birthdayError}
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
                <td><label>Пол</label></td>
                <td><select name="sex">
                    <option value="m" selected>м</option>
                    <option value="f">ж</option>
                </select></td>
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
                <td><label>Тип абонемента</label></td>
                <td colspan="3">
                    <label class="abonement">
                        <input type="radio" name="subscription" value="unlimited" checked>
                        <img src="/img/unlimit.jpg" width="20%">
                    </label>
                    <label class="abonement">
                        <input type="radio" name="subscription" value="standart">
                        <img src="/img/standart.jpg" width="20%">
                    </label>
                    <label class="abonement">
                        <input type="radio" name="subscription" value="single">
                        <img src="/img/single.jpg" width="20%">
                    </label>
                </td>
            </tr>

        </table>
        <button class="btn"  type="submit">Добавить</button>
    </form>
</@p.page>