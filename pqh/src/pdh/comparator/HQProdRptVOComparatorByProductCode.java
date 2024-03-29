package pdh.comparator;

import java.util.Comparator;

import pdh.dao.entity.VO.HQProdRptVO;

public class HQProdRptVOComparatorByProductCode implements Comparator<HQProdRptVO> {

	@Override
	public int compare(HQProdRptVO o1, HQProdRptVO o2) {
		return o1.getProductCode().compareTo(o2.getProductCode());
	}

}
