<#include "security.ftlh">

<#macro page title>
    <!DOCTYPE HTML>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${title}</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
            <#include "menu.ftlh">
    <div class="container">
        <img src="/img/fitness.jpg" width="100%">
        <div class="content">
                    <#nested>
        </div>
    </div>
    </body>
    </html>
</#macro>