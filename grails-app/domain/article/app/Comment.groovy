package article.app

class Comment {

    String comment

    String toString() { comment }

    static belongsTo = [article:Article]

    static constraints = {
        comment blank: false
    }
}
