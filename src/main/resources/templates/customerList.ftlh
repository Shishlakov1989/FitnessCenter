<#import "blocks/page.ftlh" as p>

<@p.page "Список клиентов">
    <div class="filter">
        <form method="get" action="/customer">
            <input type="text" name="filter" <#if filter??>value="${filter}"</#if>>
            <button  class="btn"  type="submit">Найти</button>
        </form>
    </div>
    <div>
        <#if customerList??>
            <table>
                <#list customerList as customer>
                    <tr>
                        <td>${customer.subscription.id}</td>
                        <td>${customer.getFio()}</td>
                        <td>${customer.getBirthdayString()}</td>
                        <td><a href="/customer/edit/${customer.id?c}">Подробнее</a></td>
                    </tr>
                </#list>
            </table>
        <#else>
            Клиенты отсутствуют
        </#if>
    </div>
</@p.page>