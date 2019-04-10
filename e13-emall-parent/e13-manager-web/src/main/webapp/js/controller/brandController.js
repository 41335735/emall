	//设置app模块中的控制器brandController
app.controller('brandController',function($scope,brandService,$controller){
		
	$controller('baseController',{$scope:$scope})
//分页 
	$scope.findPage=function(page,size){
		brandService.findPage(page,size,$scope.searchEntity).success(
			function(response){
				$scope.list=response.data.rows;//显示当前页数据 					
				$scope.paginationConf.totalItems=response.data.total;//更新总记录数 
			}		
		);				
	}


	 //新增与修改
	$scope.save=function(){
		var obj = null; 
		if($scope.entity.id != null){
			obj = brandService.update($scope.entity);
		}else{
			
			obj = brandService.save($scope.entity);
		}				
		obj.success(
			function(response){
				if(response.status==200){
					$scope.reloadList();//刷新
				}else{
					alert(response.msg);
				}				
			}		
		);
	}

	//修改前查询实体
	$scope.findById=function(id){
		brandService.findById(id).success(
			function(response){
				$scope.entity=response;
			}		
		);				
	}

	//删除
	$scope.dele=function(){
		if(confirm('确定要删除吗？')){
			brandService.dele($scope.selectIds).success(
					function(response){
						if(response.status==200){
							$scope.reloadList();//刷新
						}else{
							alert(response.msg);
						}						
					}
			);
		}					
		
	}



})