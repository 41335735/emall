package org.seller.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.common.api.ShopResult;
import org.emall.dao.TbBrandMapper;
import org.emall.entity.TbBrand;
import org.emall.entity.TbBrandExample;
import org.emall.entity.TbBrandExample.Criteria;
import org.seller.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;





@Service
public class BranServiceImpl implements BrandService {

	@Autowired
	private  TbBrandMapper brandMapper;
	
	@Override
	public List<TbBrand> findAll() {
		
		return brandMapper.selectByExample(null);
	}

	@Override
	public ShopResult queryByPager(TbBrand brand, int pageNo, int pageSize) {
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		if(brand != null) {//模糊查询
			if(brand.getName() != null && !"".equals(brand.getName())) {
				criteria.andNameLike("%" + brand.getName() +"%");
			}
			if(brand.getFirstChar() != null && !"".equals(brand.getFirstChar())) {
				criteria.andFirstCharEqualTo(brand.getFirstChar());
			}
			
		}
		PageHelper.startPage(pageNo, pageSize);//分页
		Page<TbBrand> page =  (Page<TbBrand>) brandMapper.selectByExample(example);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total", page.getTotal());
		result.put("rows", page.getResult());
		
		return ShopResult.ok(result);
	}

	@Override
	public void add(TbBrand brand) {
		brandMapper.insert(brand);
		
	}

	@Override
	public TbBrand findById(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
		
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
		
	}

}
