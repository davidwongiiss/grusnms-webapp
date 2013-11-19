/*
 * Created on 2004-6-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.device.util;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Get the parameter values by different fomats.
 * 
 * @author yongxingding
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ParamUtil {
    /**
     * Get the value as a string from the HttpServletRequest object. If it is
     * null, return the defaultValue.
     * 
     * @param request
     *            the HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @param defaultValue
     *            If the parameter you want is not in this request, return this
     *            value.
     * @return The parameter value, default is the defaultValue.
     */
    public static String getString(HttpServletRequest request,
            String paramName, String defaultValue) {
        String param = request.getParameter(paramName);
        if (param == null)
            param = defaultValue;
        return param;
    }

    /**
     * Get the value as a string from the HttpServletRequest object. If it is
     * null, return an empty string.
     * 
     * @param request
     *            The HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @return The parameter value, default is an empty string.
     */
    public static String getString(HttpServletRequest request, String paramName) {
        return getString(request, paramName, "");
    }

    /**
     * Get the value as a Integer object from the HttpServletRequest object. If
     * it is null, return the defaultValue.
     * 
     * @param request
     *            the HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @param defaultValue
     *            If the parameter you want is not in this request, return this
     *            value.
     * @return The parameter value, default is the defaultValue.
     */
    public static Integer getInt(HttpServletRequest request, String paramName,
            int defaultValue) {
        String param = request.getParameter(paramName);
        int i;
        try {
            i = Integer.parseInt(param);
        } catch (Exception e) {
            i = defaultValue;
        }
        return new Integer(i);
    }

    /**
     * Get the value as an Integer object from the HttpServletRequest object. If
     * it is null, return -1.
     * 
     * @param request
     *            The HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @return The parameter value, default is -1.
     */
    public static Integer getInt(HttpServletRequest request, String paramName) {
        return getInt(request, paramName, -1);
    }

    /**
     * Get the value as a Long object from the HttpServletRequest object. If it
     * is null, return the defaultValue.
     * 
     * @param request
     *            the HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @param defaultValue
     *            If the parameter you want is not in this request, return this
     *            value.
     * @return The parameter value, default is the defaultValue.
     */
    public static Long getLong(HttpServletRequest request, String paramName,
            long defaultValue) {
        String param = request.getParameter(paramName);
        long i;
        try {
            i = Long.parseLong(param);
        } catch (Exception e) {
            i = defaultValue;
        }
        return new Long(i);
    }

    /**
     * Get the value as a Long object from the HttpServletRequest object. If it
     * is null, return -1.
     * 
     * @param request
     *            The HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @return The parameter value, default is -1.
     */
    public static Long getLong(HttpServletRequest request, String paramName) {
        return getLong(request, paramName, -1);
    }

    /**
     * Get the value as a boolean from the HttpServletRequest object. If it is
     * null, return the defaultValue.
     * 
     * @param request
     *            the HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @param defaultValue
     *            If the parameter you want is not in this request, return this
     *            value.
     * @return The parameter value, default is the defaultValue.
     */
    public static boolean getBoolean(HttpServletRequest request,
            String paramName, boolean defaultValue) {
        String param = request.getParameter(paramName);
        boolean i;
        try {
            i = Boolean.getBoolean(param);
        } catch (Exception e) {
            i = defaultValue;
        }
        return i;
    }

    /**
     * Get the value as a boolean object from the HttpServletRequest object. If
     * it is null, return false.
     * 
     * @param request
     *            The HttpServletRequest object.
     * @param paramName
     *            The name of parameter which you want to get.
     * @return The parameter value, default is false.
     */
    public static boolean getBoolean(HttpServletRequest request,
            String paramName) {
        return getBoolean(request, paramName, false);
    }

    /**
     * Get all paramters and values in the HttpServletRequest object.
     * 
     * @param request
     *            The HttpServletRequest object.
     * @return A hashmap contains all of the parameter names and values.
     */
    public static HashMap getParams(HttpServletRequest request) {
        return getParams(request, null);
    }

    /**
     * Get parameters from the HttpServletRequest object. If prefix is not null,
     * get values witch their names include the prefix string.
     * 
     * @param request
     *            The HttpServletRequest objcet.
     * @param prefix
     *            A string of parameters' name include.
     * @return A hashmap of parameter names and values.
     */
    public static HashMap getParams(HttpServletRequest request, String prefix) {
        HashMap nextPageParams = new HashMap();
        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = (String) params.nextElement();
            if (prefix == null)
                nextPageParams.put(param, request.getParameter(param));
            else if (param.indexOf(prefix) == 0) {
                nextPageParams.put(param.substring(0), request
                        .getParameter(param));
            }
        }
        return nextPageParams;
    }


    public static Double getDouble(HttpServletRequest request,
            String paramName, double defaultValue) {
        String param = request.getParameter(paramName);
        double i;
        try {
            i = Double.parseDouble(param);
        } catch (Exception e) {
            i = defaultValue;
        }
        return new Double(i);
    }

    public static Double getDouble(HttpServletRequest request, String paramName) {
        return getDouble(request, paramName, -1);
    }

    public static Float getFloat(HttpServletRequest request, String paramName,
            double defaultValue) {
        String param = request.getParameter(paramName);
        double i;
        try {
            i = Float.parseFloat(param);
        } catch (Exception e) {
            i = defaultValue;
        }
        return new Float(i);
    }

    public static Float getFloat(HttpServletRequest request, String paramName) {
        return getFloat(request, paramName, -1);
    }

}