package article.app

class BootStrap {

    def init = { servletContext ->
        new Article(title: "How to get rich quick!", body: "Gambling!")
                .addToComments(comment: "Cool!")
                .addToComments(comment: "Correct!")
                .save()
        new Article(title: "How to get even richer!", body: "Steal!")
                .addToComments(comment: "Questionable...")
                .save()
    }
    def destroy = {
    }
}
