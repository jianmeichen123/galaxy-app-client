/**
 * 
 */
$(function () {
	$('#data-table').bootstrapTable({
		queryParamsType: 'size|page', // undefined
		pageSize:15,
		showRefresh : false ,
		sidePagination: 'server',
		method : 'post',
		pagination: true,
        search: false,
	});
	
});

