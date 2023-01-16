package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.DTO.LocationAnalysisDTO;
import com.bazi.fullystocked.Models.DTO.TopUsersDTO;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.SqlViews.ArticlesAtLocationReport;
import com.bazi.fullystocked.Repositories.ArticlesAtLocationRepository;
import com.bazi.fullystocked.Repositories.LocationsRepository;
import com.bazi.fullystocked.Services.LocationsService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationsService {
    private final ArticlesAtLocationRepository articlesAtLocationRepository;
    private final LocationsRepository locationsRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public LocationServiceImpl(ArticlesAtLocationRepository articlesAtLocationRepository, LocationsRepository locationsRepository) {
        this.articlesAtLocationRepository = articlesAtLocationRepository;
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<ArticlesAtLocationReport> findAllArticlesAtLocation(Integer locationid) {
        return articlesAtLocationRepository.findAllByLocationid(locationid);
    }

    @Override
    public List<Locations> findAll() {
        return locationsRepository.findAll();
    }

    @Override
    public List<LocationAnalysisDTO> getLocationAnalysis() {
        List<LocationAnalysisDTO> results = entityManager.createNativeQuery("""
        select q1.locationname, prihod-odliv as profit from\s
           (
            select l.locationid, l.locationname, coalesce(sum(i.price*i.quantity), 0) as prihod from project.locations l\s
            left join project.storedarticles s on s.locationid=l.locationid\s
            left join project.invoicedarticles i on i.articleid=s.articleid
            left join project.invoices i2 on i2.invoiceid=i.invoiceid and i2.datecreate between now()-interval '1 year' and now()
            group by l.locationid
            
           ) as q1
           full outer join\s
           (
            select l.locationid, l.locationname, coalesce(sum(o.price*o.quantity), 0) as odliv from project.locations l\s
            left join project.orderedarticles o on o.locationid=l.locationid\s
            left join project.orders o2 on o2.orderid=o.orderid and o2.datecreated between now()-interval '1 year' and now()
            group by l.locationid
            
           ) q2 on q2.locationid=q1.locationid
""")
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(LocationAnalysisDTO.class))
                .getResultList();
        return results;
    }
}
