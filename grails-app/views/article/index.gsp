<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'article.label', default: 'Article')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-article" class="content scaffold-list" role="main">
            <h1>Articles</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <g:each in="${articleList}" var="articleItem">
                    <tr>
                    <td><g:link class="show" action="show" resource="${articleItem}">${articleItem.title}</g:link></td>
                    <g:form controller="article" resource="${articleItem}" method="POST">
                        <td><g:actionSubmit value="View" action="show"/></td>
                        <td><g:actionSubmit value="Edit" action="edit"/></td>
                    </g:form>
                    <g:form controller="article" resource="${articleItem}" action="delete">
                        <td><g:actionSubmit value="Delete"/></td>
                    </g:form>
                    </tr>
                </g:each>
            </table>
            <div class="pagination">
                <g:paginate total="${articleCount ?: 0}" />
            </div>
        </div>

    <div id="create-article">
        <h1><g:message code="default.create.label" args="[entityName]" /></h1>
        <g:hasErrors bean="article">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.article}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <table>
            <g:form controller="article" method="post">
                <tr><td><g:textField name="title" value="" /></td></tr>
                <tr><td><g:textArea name="body" value="" rows="10" cols="5000"/></td></tr>
                <tr><td><g:actionSubmit value="Create" action="save" /></td></tr>
            </g:form>
        </table>
    </div>
    </body>
</html>