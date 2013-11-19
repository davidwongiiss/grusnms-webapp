/**
* Project:		合力工单
* Author:		童贝
* Company: 		北京合力金桥软件技术有限责任公司
* Created Date:	2011 12 1
* 
* Copyright @ 2011 hollycrm – Confidential and Proprietary
* 
* History:
* ------------------------------------------------------------------------------
* Version|  Date       |time	|  Author |           Change Description          */

package com.device.util;

import org.codehaus.jackson.map.annotate.JsonFilter;
/**
 * 
 * 功能说明: 用来过滤序列化的对象
 * 创建时间: 2011 12 10 9:46:48
 * @author 童贝
 */
@JsonFilter(PropertyFilterMixIn.MODEL_FILTER)
public class PropertyFilterMixIn {
	public static final String MODEL_FILTER="modelfilter";
}
