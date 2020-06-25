/*
 * @Description: '主页'所有请求
 * @Author: 任录
 * @Date: 2019-07-22 15:13:52
 * @LastEditTime: 2019-07-26 10:02:37
*/

import * as http from './base'
import request from '@/utils/request'

// 查询登录日志列表
export function list() {
  return request({
    url: '/welcome/notice/list',
    method: 'get',
  })
}
export function getNoticeType() {
  return request({
    url: '/welcome/notice/getNoticeType',
    method: 'get',
  })
}

export function getInfo(id) {
  return request({
    url: '/welcome/notice/'+id,
    method: 'get',
  })
}
export function getImage(html) {
  if(html !== undefined){
    let serverSrc = 'https://images.weserv.nl/?url='
    let srcReg = /src=([\'\"]?([^\'\"]*)[\'\"]?)/ig;


    html = html.replace(srcReg,"src='"+serverSrc+"$2"+"'").replace(/\\/g,'');
    html = html.replace(/\<img/gi, '<img style="width:auto;height:auto;margin-left: 25%" mode="aspectFit" ');

    html = html.replace(/\url\(&apos;/g,'url(&apos;https://images.weserv.nl/?url=');

    return html;
  }
}



