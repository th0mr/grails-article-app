package article.app

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ArticleController {

    ArticleService articleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond articleService.list(params), model:[articleCount: articleService.count()]
    }

    def show(Long id) {
        respond articleService.get(id)
    }

    def create() {
        respond new Article(params)
    }

    def save(Article article) {
        if (article == null) {
            notFound()
            return
        }

        try {
            articleService.save(article)
        } catch (ValidationException e) {
            respond article.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*' { respond article, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond articleService.get(id)
    }

    def update(Article article) {
        if (article == null) {
            notFound()
            return
        }

        try {
            articleService.save(article)
        } catch (ValidationException e) {
            respond article.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*'{ respond article, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        articleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'article.label', default: 'Article'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def postComment(Long id, String commentString){
        if (id == null) {
            notFound()
            return
        }

        Article article = articleService.get(id)
        Comment comment = new Comment(comment:commentString, article:article).save()
        article.addToComments(comment)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*'{ respond article, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label', default: 'Article'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
