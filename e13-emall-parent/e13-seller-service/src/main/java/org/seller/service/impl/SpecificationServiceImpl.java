package org.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.common.api.ShopResult;
import org.emall.dao.TbSpecificationMapper;
import org.emall.dao.TbSpecificationOptionMapper;
import org.emall.entity.Specification;
import org.emall.entity.TbSpecification;
import org.emall.entity.TbSpecificationExample;
import org.emall.entity.TbSpecificationExample.Criteria;
import org.emall.entity.TbSpecificationOption;
import org.emall.entity.TbSpecificationOptionExample;
import org.seller.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SpecificationServiceImpl implements SpecificationService{
	@Autowired
	private TbSpecificationMapper SpecificationService;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 分页全查及模糊查询
	 */
	@Override
	public ShopResult queryByPager(TbSpecification specification, int pageNo, int pageSize) {
		TbSpecificationExample example = new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		if(specification != null) {//模糊查询
			if(specification.getSpecName() != null && specification.getSpecName().length()>0) {
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

	/**
	 * 新增
	 */
	@Override
	public void add(Specification specification) {
		TbSpecification tbSpecification = specification.getSpecification();
		SpecificationService.insert(tbSpecification);
		
		for(TbSpecificationOption so : specification.getSpecificationOptionList()) {
			so.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insert(so);
		}
	}

	
	@Override
	public Specification findById(Long id) {
		Specification sf = new Specification();
		sf.setSpecification(SpecificationService.selectByPrimaryKey(id));
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		org.emall.entity.TbSpecificationOptionExample.Criteria criteria =example.createCriteria();
		criteria.andSpecIdEqualTo(id);
		sf.setSpecificationOptionList(specificationOptionMapper.selectByExample(example));
		return sf;
	}

	@Override
	public void update(Specification specification) {
		//获取规格实体类
		TbSpecification tbSpecification =specification.getSpecification();
		SpecificationService.updateByPrimaryKey(tbSpecification);
		
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		org.emall.entity.TbSpecificationOptionExample.Criteria criteria =example.createCriteria();
		criteria.andSpecIdEqualTo(tbSpecification.getId());
		specificationOptionMapper.deleteByExample(example);
		
		for(TbSpecificationOption so : specification.getSpecificationOptionList()) {
			so.setSpecId(tbSpecification.getId());//设置规格id
			specificationOptionMapper.insert(so);//新增规格
		}
		
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			org.emall.entity.TbSpecificationOptionExample.Criteria criteria =example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
			SpecificationService.deleteByPrimaryKey(id);
		}
		
	}
	
	
	

}
