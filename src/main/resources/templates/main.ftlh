<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post">
            <input type="text" name="resource" placeholder="Введите название ресурса">
            <input type="text" name="login" placeholder="Введите логин">
            <input type="password" name="password" placeholder="Введите пароль">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список паролей</div>
    <div>
        <form method="get" action="/main">
            <input type="text" name="resource" placeholder="Введите название ресурса" value="${resource}">
            <button type="submit">Найти</button>
        </form>
    </div>
    <#list passwords as password>
        <div>
            <b>${password.resource}</b>
            <span>${password.login}</span>
            <span>${password.password}</span>
            <strong>${password.ownerName}</strong>
        </div>
    <#else>
No passwords
    </#list>
</@c.page>
