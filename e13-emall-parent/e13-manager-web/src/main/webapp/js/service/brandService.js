app.service('brandService',function($http){
		this.findPage=function(page,size,searchEntity){
			return $http.post('../brand/findByPager?page='+page +'&size='+size, searchEntity);
		}
		this.save = function(entity){
			
			return $http.post('../brand/add',entity);
		}
		this.update = function(entity){
			return $http.post('../brand/update',entity);
			
		}
		this.findById = function(id){
			
			return $http.get('../brand/findById?id='+id);
		}
		this.dele = function(selectIds){
			return $http.get('../brand/delete?ids='+selectIds);
		}
		
})