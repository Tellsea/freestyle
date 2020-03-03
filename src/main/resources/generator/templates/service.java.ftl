package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* ${table.comment!} Service接口
*
* @author ${author}
* @date ${date}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>