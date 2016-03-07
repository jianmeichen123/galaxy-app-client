/**
 * 
 */
$(function () {
	$('#data-table').bootstrapTable({
		queryParamsType: 'size|page', // undefined
	});
	
});

function del(index,row){
	var del_by = $(this).attr("del-by"); 
	console.log(del_by)
	var a = '<a action="delete" data-id='+row[del_by]+' class="bluebtn ico cx" href="javascript:void(0)">'+ "删除"+'</a>'
	return a;
}