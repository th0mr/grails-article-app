package article.app

class Article {

    String title
    String body

    String toString() { title }

    static hasMany = [comments:Comment]

    static constraints = {
        title blank: false
        body blank: false
        body type: 'text'
    }
}
