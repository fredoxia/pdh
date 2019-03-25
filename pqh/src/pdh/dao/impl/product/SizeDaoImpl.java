package pdh.dao.impl.product;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pdh.dao.entity.product.Color;
import pdh.dao.entity.product.Size;
import pdh.dao.impl.BaseDAO;

@Repository
public class SizeDaoImpl extends BaseDAO<Size> {



}
