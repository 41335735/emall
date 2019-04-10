app.service('specificationService',function($http){
		this.findPage=function(page,size,searchEntity){
			return $http.post('../specification/findByPager?page='+page +'&size='+size, searchEntity);
		}
		this.save = function(entity){
			
			return $http.post('../specification/add',entity);
		}
		this.update = function(entity){
			return $http.post('../specification/update',entity);
			
		}
		this.findById = function(id){
			
			return $http.get('../specification/findById?id='+id);
		}
		this.dele = function(selectIds){
			return $http.get('../specification/delete?ids='+selectIds);
		}
		
})