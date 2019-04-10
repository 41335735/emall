package org.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.common.api.ShopResult;
import org.emall.dao.TbSpecificationMapper;
import org.emall.entity.TbSpecification;
import org.emall.entity.TbSpecificationExample;
import org.emall.entity.TbSpecificationExample.Criteria;
import org.seller.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SpecificationServiceImpl implements SpecificationService{
	@Autowired
	private TbSpecificationMapper SpecificationService;

	@Override
	public ShopResult queryByPager(TbSpecification specification, int pageNo, int pageSize) {
		TbSpecificationExample example = new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		if(specification != null) {//模糊查询
			if(specification.getSpecName() != null && !"".equals(specification.getSpecName())) {
				criteria.andSpecNameLike("%" + specification.getSpecName() +"%");
			}
			
			
		}
		
		PageHelper.startPage(pageNo, pageSize);//分页
		Page<TbSpecification> page =  (Page<TbSpecification>) SpecificationService.selectByExample(example);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		return ShopResult.ok(result);
	}

	@Override
	public void add(TbSpecification specification) {
		SpecificationService.insert(specification);
		
	}

	@Override
	public TbSpecification findById(Long id) {
		return SpecificationService.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbSpecification specification) {
		SpecificationService.updateByPrimaryKey(specification);
		
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			SpecificationService.deleteByPrimaryKey(id);
		}
		
	}
	
	
	

}
