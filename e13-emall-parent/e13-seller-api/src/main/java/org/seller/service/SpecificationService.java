package org.seller.service;

import org.common.api.ShopResult;
import org.emall.entity.Specification;
import org.emall.entity.TbSpecification;

public interface SpecificationService {

		//分页查询
		public ShopResult queryByPager(TbSpecification specification , int page , int size);
		
		//新增
		public void add(Specification specification);
		
		//修改前查询
		public Specification findById(Long id);
		
		//修改品牌
		public void update(Specification specification);
		
		//批量删除
		public  void delete(Long[] ids);
}
