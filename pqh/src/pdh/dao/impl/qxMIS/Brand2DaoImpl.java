package pdh.dao.impl.qxMIS;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pdh.dao.entity.qxMIS.Brand2;
import pdh.dao.impl.BaseDAO2;

@Repository
public class Brand2DaoImpl extends BaseDAO2<Brand2> {

	public List<Brand2> getAllHq() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Brand2.class);
		criteria.add(Restrictions.isNull("chainId"));
		return this.getByCritera(criteria, true);
	}


}
