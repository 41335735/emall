package org.seller.service;

import java.util.List;

import org.common.api.ShopResult;
import org.emall.entity.TbBrand;

public interface BrandService {

	//全查
	public List<TbBrand> findAll();
	
	//分页查询
	public ShopResult queryByPager(TbBrand brand , int page , int size);
	
	//新增
	public void add(TbBrand brand);
	
	//修改前查询
	public TbBrand findById(Long id);
	
	//修改品牌
	public void update(TbBrand brand);
	
	//批量删除品牌
	public  void delete(Long[] ids);
}
