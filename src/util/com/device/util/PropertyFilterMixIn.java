/**
* Project:		��������
* Author:		ͯ��
* Company: 		��������������������������ι�˾
* Created Date:	2011 12 1
* 
* Copyright @ 2011 hollycrm �C Confidential and Proprietary
* 
* History:
* ------------------------------------------------------------------------------
* Version|  Date       |time	|  Author |           Change Description          */

package com.device.util;

import org.codehaus.jackson.map.annotate.JsonFilter;
/**
 * 
 * ����˵��: �����������л��Ķ���
 * ����ʱ��: 2011 12 10 9:46:48
 * @author ͯ��
 */
@JsonFilter(PropertyFilterMixIn.MODEL_FILTER)
public class PropertyFilterMixIn {
	public static final String MODEL_FILTER="modelfilter";
}
