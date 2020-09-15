<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getEmployee().getFio()
    isAdmin = user.isAdmin()
    >
    <#if user.employee.isActive()>
        <#assign
        isLogged = true
        >
    </#if>
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    isLogged = false
    >
</#if>