<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'article.label', default: 'Article')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-article" class="content scaffold-show" role="main">
            <h1>${this.article.title}</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            ${this.article.body}
            <h2>Comments</h2>
            <table>
            <g:each in="${this.article.comments}" var="comment">
                <tr>
                    <td>${comment.comment}</td><td>
                    <g:form controller="comment" resource="${comment}" action="delete">
                        <td><g:actionSubmit value="Delete"/></td>
                    </g:form>
                </tr>
            </g:each>
            </table>

            <div id="create-comment">
                <h1>Post a comment!</h1>
                <g:hasErrors bean="comment">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${this.comment}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <table>
                    <g:form resource="${this.article}" method="post">
                        <tr><td><g:textField name="commentString" value="" /></td></tr>
                        <tr><td><g:actionSubmit value="Post" action="postComment"/></td></tr>
                    </g:form>
                </table>
            </div>

        </div>
    </body>
</html>
