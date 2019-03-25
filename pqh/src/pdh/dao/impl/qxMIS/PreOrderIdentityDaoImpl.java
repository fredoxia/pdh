package pdh.dao.impl.qxMIS;

import org.springframework.stereotype.Repository;

import pdh.dao.entity.qxMIS.CustPreorderIdentity;
import pdh.dao.impl.BaseDAO2;


@Repository
public class PreOrderIdentityDaoImpl extends BaseDAO2<CustPreorderIdentity> {
	
	@Override
	public void saveOrUpdate(CustPreorderIdentity entity, boolean cached) {
		if (cached)
			getHibernateTemplateMS().setCacheQueries(true);

		getHibernateTemplateMS().saveOrUpdate(entity);
	}
	
}
