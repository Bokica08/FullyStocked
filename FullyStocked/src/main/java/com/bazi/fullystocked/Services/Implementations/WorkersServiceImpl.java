package com.bazi.fullystocked.Services.Implementations;

import com.bazi.fullystocked.Models.DTO.ArticleAnalysisDTO;
import com.bazi.fullystocked.Models.DTO.TopUsersDTO;
import com.bazi.fullystocked.Models.Exceptions.InvalidArgumentsException;
import com.bazi.fullystocked.Models.Locations;
import com.bazi.fullystocked.Models.Workers;
import com.bazi.fullystocked.Repositories.LocationsRepository;
import com.bazi.fullystocked.Repositories.WorkersRepository;
import com.bazi.fullystocked.Services.WorkersService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class WorkersServiceImpl implements WorkersService {
    private final WorkersRepository workersRepository;
    private final LocationsRepository locationsRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public WorkersServiceImpl(WorkersRepository workersRepository, LocationsRepository locationsRepository) {
        this.workersRepository = workersRepository;
        this.locationsRepository = locationsRepository;
    }

    @Override
    public Optional<Workers> assignLocation(Integer workerId, Integer locationId) {
        Workers worker=workersRepository.findById(workerId).orElseThrow(InvalidArgumentsException::new);
        Locations location=locationsRepository.findById(locationId).orElseThrow(InvalidArgumentsException::new);
        worker.setLocation(location);
        return Optional.of(workersRepository.save(worker));
    }

    @Override
    public List<Workers> findAllWithNoLocation() {
        return workersRepository.findAllByLocationIsNull();
    }

    @Override
    public List<TopUsersDTO> findAllTopUsers() {
        List<TopUsersDTO> results = entityManager.createNativeQuery("""
 select u.firstname, u.lastname, u.username, u.email, coalesce(max(ti.totalPrice), 0) as topInvoiceSum,
  (select a.articlename from project.articles a
      left join project.invoicedarticles i2 on i2.articleid=a.articleid
      where i2.price*i2.quantity=max(ba.totalPrice) and i2.invoiceid = min(ti.invoiceid)
      ) as topArticleName,
  (select i2.price  from project.articles a
      left join project.invoicedarticles i2 on i2.articleid=a.articleid
      where i2.price*i2.quantity=max(ba.totalPrice) and i2.invoiceid = min(ti.invoiceid)
      ) as topArticlePrice,
  (select i2.quantity from project.articles a
      left join project.invoicedarticles i2 on i2.articleid=a.articleid
      where i2.price*i2.quantity=max(ba.totalPrice) and i2.invoiceid = min(ti.invoiceid)
      ) as topArticleQuantity,
  max(ba.totalPrice) as topArticleTotalPrice from project.workers w
          left join project.users u on u.userid=w.userid
          left join
              (
                  select i.invoiceid, i.workeruserid , sum(ia.price*ia.quantity) as totalPrice from project.invoices i
                  left join project.invoicedarticles ia on ia.invoiceid=i.invoiceid
                  group by i.invoiceid, i.workeruserid
              ) ti on ti.workeruserid=u.userid
          left join
              (
                  select ia2.invoiceid, a.articlename, ia2.price as price, ia2.quantity as quantity, ia2.price*ia2.quantity as totalPrice from project.invoicedarticles ia2
                  left join project.articles a on a.articleid=ia2.articleid
              ) ba on ba.invoiceid=ti.invoiceid
          group by u.userid
          order by topInvoiceSum desc
""")
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(TopUsersDTO.class))
                .getResultList();
        return results;
    }


}
