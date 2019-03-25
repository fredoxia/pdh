package pdh.dao.impl.qxMIS;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import pdh.dao.entity.qxMIS.Year2;
import pdh.dao.impl.BaseDAO2;
@Repository
public class Year2DaoImpl extends BaseDAO2<Year2>{
	
	public List<Year2> getAllByOrder(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Year2.class);
		criteria.addOrder(Order.desc("year"));
		
		List<Year2> years = getByCritera(criteria, true);
		if (years.size() > 2)
			return years.subList(0, 2);
		else 
			return years;
		
	}

}
