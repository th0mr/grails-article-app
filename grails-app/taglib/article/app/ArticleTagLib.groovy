package article.app

class ArticleTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def PrintCommentCount = {attrs ->
        out << "Comments: ${attrs?.article.comments.size()}"
    }

}
