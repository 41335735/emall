package org.manager.controller;

import java.util.List;

import org.common.api.ShopResult;
import org.emall.entity.TbBrand;
import org.seller.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;


@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		return brandService.findAll();
	}
	@RequestMapping("/findByPager")
	public ShopResult findByPager(@RequestBody TbBrand brand , int page , int size){
		return brandService.queryByPager(brand, page, size);
	}
		
	@RequestMapping("/add")
	public ShopResult add(@RequestBody TbBrand brand){
		try {
			brandService.add(brand);
			return ShopResult.build(200, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			
			return ShopResult.build(500, "增加失败");
		} 
	}
	
	@RequestMapping("/findById")
	public TbBrand findById(Long id) {
		return brandService.findById(id);
	}
	@RequestMapping("/update")
	public ShopResult update(@RequestBody TbBrand brand){
		try {
			brandService.update(brand);
			return ShopResult.build(200, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500, "修改失败");
		} 
	}
	@RequestMapping("/delete")
	public ShopResult delete(Long[] ids){
		try {
			brandService.delete(ids);
			return ShopResult.build(200," 删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ShopResult.build(500, "删除失败");
		} 
	}
}
