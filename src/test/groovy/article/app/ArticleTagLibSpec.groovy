package article.app

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class ArticleTagLibSpec extends Specification implements TagLibUnitTest<ArticleTagLib> {
    Article testArticle = new Article(title:"testTitle", body:"testBody")
    def setup() {
    }

    def cleanup() {
    }

    void "the tag reports articles with no comments as Comments: 0"() {
        when:
        testArticle.comments = []

        then:
        tagLib.printCommentCount(article:testArticle) == "Comments: 0"
    }

    @Unroll
    void "the tag reports articles with #n comments as Comments: #pn"() {
        when:
        Comment[] comments = new Comment[n]
        for (int i = 0; i < n; i++){
            comments[i] = new Comment(comment:"testComment${n}", article:testArticle)
        }
        testArticle.comments = comments

        then:
        tagLib.printCommentCount(article:testArticle) == "Comments: ${n}"

        where:
        n << [1, 2, 5, 10, 100]
    }
}
