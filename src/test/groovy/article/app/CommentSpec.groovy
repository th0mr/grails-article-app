package article.app

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CommentSpec extends Specification implements DomainUnitTest<Comment> {

    Article a = new Article(title:"testTitle", body:"testBody")
    Comment c = new Comment(comment:"testComment", article:a)

    def setup() {
    }

    def cleanup() {
    }

    void "the default comment is valid"() {
        expect:
        c.validate()
        c.comment == "testComment"
        c.article == a
    }

    void "comments without a parent article are invalid"() {
        when:
        c.article = null

        then:
        !c.validate()
    }

    void "a blank comment is invalid"() {
        when:
        c.comment = ""

        then:
        !c.validate()
    }
}
