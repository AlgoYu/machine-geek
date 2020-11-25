import { request } from "./Request.js"

// 增加${moduleName}
export function add(data,callback){
    request("post","/${instanceName}/add",data,callback);
}

// 根据ID删除${moduleName}
export function deleteById(data,callback){
    request("delete","/${instanceName}/deleteById",data,callback);
}

// 根据ID更新${moduleName}
export function modifyById(data,callback){
    request("put","/${instanceName}/modifyById",data,callback);
}

// 根据ID获取${moduleName}
export function getById(data,callback){
    request("get","/${instanceName}/getById",data,callback);
}

// 获取所有${moduleName}
export function list(callback){
    request("get","/${instanceName}/list",null,callback);
}

// 获取所有${moduleName}
export function paging(data,callback){
    request("get","/${instanceName}/list",data,callback);
}