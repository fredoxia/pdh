package pdh.comparator;

import java.util.Comparator;

import pdh.dao.entity.VO.CustOrderProductVO;
import pdh.dao.entity.VO.CustSummaryDataVO;
import pdh.dao.entity.order.CustOrderProduct;
import pdh.dao.entity.product.Product;

public class CustSummaryDataVOComparatorBySum implements Comparator<CustSummaryDataVO> {

	@Override
	public int compare(CustSummaryDataVO o1, CustSummaryDataVO o2) {
		return o2.getSumQ() - o1.getSumQ();
	}

}
