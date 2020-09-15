<#import "blocks/page.ftlh" as p>

<@p.page "Страница клиента">
        <h5>Карта клиента #${customer.subscription.id?c}</h5>
        <form action="/customer/edit/${customer.id?c}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <table class="info" border="1">
                <tr>
                    <td rowspan="6" width="10%">
                        <#if customer.photo??>
                            <img id="foto" src="/photo/${customer.photo}" width="100%">
                        <#else>
                            Фотография отсутствует
                        </#if>
                    </td>
                    <td>Фамилия</td>
                    <td width="23%">
                        <input type="text" name="lastName" value="${customer.lastName}">
                        <#if lastNameError??>
                        <div class="error">
                            ${lastNameError}
                        </div>
                        </#if>
                    </td>
                    <td width="22%">Абонемент</td>
                    <td width="23%">${customer.subscription.type.title}/<#if customer.subscription.active>Активен<#else>
                        Неактивен</#if></td>
                </tr>
                <tr>
                    <td width="22%">Имя</td>
                    <td width="23%">
                        <input type="text" name="firstName" value="${customer.firstName}">
                        <#if firstNameError??>
                            <div class="error">
                                ${firstNameError}
                            </div>
                        </#if>
                    </td>
                    <td width="22%">Дата регистрации</td>
                    <td width="23%"><input type="date" value="${customer.subscription.startSubscription}" disabled></td>
                </tr>
                <tr>
                    <td width="22%">Отчество</td>
                    <td width="23%">
                        <input type="text" name="middleName" value="${customer.middleName}">
                        <#if fmiddleNameError??>
                        <div class="error">
                            ${middleNameError}
                        </div>
                        </#if>
                    </td>
                    <td width="22%">Дата окончания</td>
                    <td width="23%"><input type="date" value="${customer.subscription.endSubscription}" disabled></td>
                </tr>
                <tr>
                    <td width="22%">Пол</td>
                    <td width="23%">${customer.getSexString()}</td>
                    <td width="22%">Осталось занятий</td>
                    <td width="23%"><#if customer.subscription.leftVisits == -1>
                        Неограничено<#else>${customer.subscription.leftVisits}</#if></td>
                </tr>
                <tr>
                    <td width="22%">Дата рождения</td>
                    <td width="23%">
                        <input class="form-left" type="date" name="birthday" value="${customer.birthday}"/>
                        <#if birthdayError??>
                        <div class="error">
                            ${birthdayError}
                        </div>
                        </#if>
                    </td>
                    <td width="22%">Текущая скидка</td>
                    <td width="23%">${customer.subscription.getCurrentDiscount()}%</td>
                </tr>
                <tr>
                    <td width="22%">Телефон</td>
                    <td width="23%"><input class="form-left" type="text" name="phoneNumber"
                                           value="+${customer.phoneNumber}"/>
                        <#if phoneNumberError??>
                        <div class="error">
                            ${phoneNumberError}
                        </div>
                        </#if>
                    </td>
                    <td width="22%">Было посещений</td>
                    <td width="23%">${customer.subscription.countVisit}</td>
                </tr>
            </table>
            <button type="submit">Изменить</button>
        </form>
    <form method="post" action="/upload/${customer.id?c}" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div>
            <input class="uploadFile" type="file" name="file" accept="image/*"/>
            <button type="submit"><#if customer.photo??>Обновить<#else>Загрузить</#if></button>
        </div>
    </form>
    <div>
        <table style="width: 90%; margin: 0 auto" border="1">
            <tr>
                <td width="50%">
                    <label>Текущая стоимость продления</label>
                    <ul>
                        <#list typesSubscr as type>
                            <li>${type.title} - ${type.getCurrentPrice(customer.subscription.discount)} руб.</li>
                        </#list>
                    </ul>
                </td>
                <td width="50%">
                    <form action="/subscription/prolongation/${customer.subscription.id?c}" method="get">
                        Продление абонемента<br/>
                        <label>Тип абонемента</label><br/>
                        <select name="type">
                            <option value="unlimited" selected>Безлимитный</option>
                            <option value="standart">Стандартный</option>
                            <option value="single">Разовое посещение</option>
                        </select>
                        <br/>
                        <button type="submit">Продлить</button>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</@p.page>