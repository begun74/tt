

function delObject(id,clazz,act)
{
	if(confirm('Delete record?'))location.href='admin/delObject?id='+id+'&clazz='+clazz+'&act='+act;
}

