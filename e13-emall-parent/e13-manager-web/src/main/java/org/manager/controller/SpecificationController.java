package org.manager.controller;

import org.common.api.ShopResult;
import org.emall.entity.TbSpecification;
import org.seller.service.SpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
	@Reference
	private SpecificationService specificationService;
	
	@RequestMapping("/findByPager")
	public ShopResult findByPager(@RequestBody TbSpecification specification , int page , int size){
		return specificationService.queryByPager(specification, page, size);
	}
	
	@RequestMapping("/add")
	public ShopResult add(@RequestBody TbSpecification specification ){
		try {
			specificationService.add(specification);;
			return ShopResult.build(200, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			
			return ShopResult.build(500, "增加失败");
		} 
	}
	
	@RequestMapping("/findById")
	public TbSpecification findById(Long id) {
		return specificationService.findById(id);
	}
	@RequestMapping("/update")
	public ShopResult update(@RequestBody TbSpecification specification){
		try {
			specificationService.update(specification);
			return ShopResult.build(200, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500, "修改失败");
		} 
	}
	@RequestMapping("/delete")
	public ShopResult delete(Long[] ids){
		try {
			specificationService.delete(ids);
			return ShopResult.build(200," 删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500, "删除失败");
		} 
	}
}
