<#import "blocks/page.ftlh" as p>

<@p.page "Главная страница">
    <div class="filter">
        <form action="/" method="get">
            <input type="text" name="subscription">
            <button class="btn"  type="submit">Найти</button>
        </form>
    </div>
    <#if subscription??>
        <div>
            <table>
                <tr>
                    <td>
                        ${subscription.id?c}
                    </td>
                    <td>
                        ${subscription.customer.getLastName()}
                    </td>
                    <td>
                        ${subscription.customer.getFirstName()}
                    </td>
                    <td>
                        ${subscription.customer.getMiddleName()}
                    </td>
                    <td>
                        ${subscription.customer.getBirthdayString()}
                    </td>
                    <td>
                        <a href="/subscription/${subscription.id?c}/training">
                            <button>Начать тренировку</button>
                        </a>
                    </td>
                </tr>
            </table>
        </div>
    </#if>
    <#if inGym??>
        <div>
            <table>
                <#list inGym as subscr>
                    <tr>
                        <td>
                            ${subscr.id?c}
                        </td>
                        <td>
                            ${subscr.customer.getLastName()}
                        </td>
                        <td>
                            ${subscr.customer.getFirstName()}
                        </td>
                        <td>
                            ${subscr.customer.getMiddleName()}
                        </td>
                        <td>
                            ${subscr.customer.getBirthdayString()}
                        </td>
                        <td>
                            <a href="/subscription/${subscr.id?c}/training">
                                <button>Окончить тренировку</button>
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    <#else>
        <div>Клиентов в зале нет</div>
    </#if>
</@p.page>