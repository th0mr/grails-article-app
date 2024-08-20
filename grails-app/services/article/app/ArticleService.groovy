package article.app

import grails.gorm.services.Service

@Service(Article)
interface ArticleService {

    Article get(Serializable id)

    List<Article> list(Map args)

    Long count()

    void delete(Serializable id)

    Article save(Article article)

}