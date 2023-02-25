package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.Articles;
import com.bazi.fullystocked.Models.Categories;
import com.bazi.fullystocked.Models.DTO.ArticleAnalysisDTO;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Models.SqlViews.SupplierSuppliesArticleReport;
import com.bazi.fullystocked.Repositories.ArticlesAtLocationRepository;
import com.bazi.fullystocked.Repositories.ArticlesRepository;
import com.bazi.fullystocked.Repositories.CategoriesRepository;
import com.bazi.fullystocked.Repositories.SupplierSuppliesArticleRepository;
import com.bazi.fullystocked.Services.ArticlesService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticlesRepository articlesRepository;
    private final CategoriesRepository categoriesRepository;
    private final SupplierSuppliesArticleRepository supplierSuppliesArticleRepository;
    private final ArticlesAtLocationRepository articlesAtLocationRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ArticlesServiceImpl(ArticlesRepository articlesRepository, CategoriesRepository categoriesRepository, SupplierSuppliesArticleRepository supplierSuppliesArticleRepository, ArticlesAtLocationRepository articlesAtLocationRepository) {
        this.articlesRepository = articlesRepository;
        this.categoriesRepository = categoriesRepository;
        this.supplierSuppliesArticleRepository = supplierSuppliesArticleRepository;
        this.articlesAtLocationRepository = articlesAtLocationRepository;
    }

    @Override
    @Transactional
    public Optional<Articles> create(String description, String articlename, int maxquantityperlocation) {
        if(description==null || description.isEmpty() || articlename==null || articlename.isEmpty() || maxquantityperlocation<=0)
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(articlesRepository.saveAndFlush(new Articles(description, articlename, maxquantityperlocation)));
    }

    @Override
    @Transactional
    public Optional<Articles> create(String description, String articlename, String imageurl, int maxquantityperlocation) {
        if(description==null || description.isEmpty() || articlename==null || articlename.isEmpty() || maxquantityperlocation<=0)
        {
            throw new InvalidArgumentsException();
        }
        return Optional.of(articlesRepository.saveAndFlush(new Articles(description, articlename, imageurl, maxquantityperlocation)));
    }

    @Override
    @Transactional
    public Optional<Articles> addToCategory(Integer articleId, Integer categoryId) {
        Categories category=categoriesRepository.findById(categoryId).orElseThrow(InvalidArgumentsException::new);
        Articles articles=articlesRepository.findById(articleId).orElseThrow(InvalidArgumentsException::new);
        if(articles.getCategoryList().contains(category))
        {
            return Optional.of(articles);
        }
        articles.getCategoryList().add(category);
        articlesRepository.save(articles);
        return Optional.of(articles);
    }

    @Override
    public List<Categories> findAllCategoriesByArticle(Integer articleId) {
        Articles articles=articlesRepository.findById(articleId).orElseThrow(InvalidArgumentsException::new);
        return articles.getCategoryList();
    }

    @Override
    public List<Articles> findAll() {
        return articlesRepository.findAll();
    }

    @Override
    public List<SupplierSuppliesArticleReport> findAllBySupplier(Integer supplierId) {
        return supplierSuppliesArticleRepository.findAllById_Userid(supplierId);
    }

    @Override
    public List<ArticlesAtLocationReport> findAvailabilityAtAllLocations(Integer id) {
        return articlesAtLocationRepository.findAllByArticleid(id);
    }

    @Override
    public List<ArticleAnalysisDTO> getArticleAnalysis() {
        List<ArticleAnalysisDTO> results = entityManager.createNativeQuery("""
        select a.articlename, l.locationname,
        (case
            when q1.dostapnost+coalesce(q2.incoming, 0)<coalesce(q3.sold, 0) then 'Yes'
            else 'No'
        end
        ) as defict from project.articles a
       left join project.storedarticles s on s.articleid=a.articleid
       left join project.locations l on l.locationid=s.locationid
       left join (
        select articleid, locationid, coalesce(sum(quantity), 0) as dostapnost from project.storedarticles s
        group by s.sarticleid, s.locationid
       ) q1 on q1.articleid=a.articleid and q1.locationid=l.locationid
       left join (
        select articleid, locationid, coalesce(sum(quantity), 0) as incoming from project.orderedarticles o
        where o.articlestatus='ORDERED' or o.articlestatus='DELIVERED'
        group by o.articleid, o.locationid
       ) q2 on q2.articleid=a.articleid and q2.locationid=l.locationid
       left join (
        select i.articleid, l.locationid, coalesce(sum(quantity), 0) as sold from project.invoicedarticles i
        left join project.invoices inv on inv.invoiceid=i.invoiceid and inv.datecreate between now()-interval '1 month' and now()
        left join project.workers w on w.userid=inv.workeruserid
        left join project.locations l on l.locationid=w.locationid
        group by i.articleid, l.locationid
       )q3 on q3.articleid=a.articleid and q3.locationid=l.locationid
       order by a.articlename\s
""")
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(ArticleAnalysisDTO.class))
                .getResultList();
        return results;
    }
}
