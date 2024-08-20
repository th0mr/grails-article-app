package article.app

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ArticleSpec extends Specification implements DomainUnitTest<Article> {

    Article a = new Article(title:"testTitle", body:"testBody")

    def setup() {
    }

    def cleanup() {
    }

    void "default article is valid"() {
        expect:
        a.validate()
        a.title == "testTitle"
        a.body == "testBody"
    }

    void "articles with comments are valid"() {
        when:
        a.addToComments(new Comment(comment:"testComment"))

        then:
        a.validate()
    }

    void "title must not be blank"() {
        when:
        a.title = ""

        then:
        !a.validate()
        a.errors.getFieldError('title').code == 'blank'
    }

    void "body must not be blank"() {
        when:
        a.body = ""

        then:
        !a.validate()
        a.errors.getFieldError('body').code == 'blank'
    }
}
