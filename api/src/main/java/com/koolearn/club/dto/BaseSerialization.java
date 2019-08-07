package com.koolearn.club.dto;

import com.alibaba.fastjson.JSON;
import com.koolearn.club.utils.RandomUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by miaoyoumeng on 2017/10/29.
 */
public class BaseSerialization implements Serializable {
    private static final long serialVersionUID = -6802440833595117881L;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public void dealNull()  {
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(this.getClass(),
                    Object.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Method readMethod = pd.getReadMethod();
            Object value;
            try {
                value = readMethod.invoke(this);
                Method writeMethod = pd.getWriteMethod();
                Class clazz = pd.getPropertyType();
                if(isBaseSerializationClass(clazz)){
                    if(null != value){
                        BaseSerialization baseSerialization = (BaseSerialization)value;
                        baseSerialization.dealNull();
                    }
                }
                if(null == value){
                    if(clazz == String.class){
                        writeMethod.invoke(this,"");
                    }
                    if(clazz == Integer.class){
                        writeMethod.invoke(this,-1);
                    }
                    if(clazz == Short.class){
                        writeMethod.invoke(this, (short)-1);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    public void randomData()  {
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(this.getClass(),
                    Object.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Method readMethod = pd.getReadMethod();
            Object value;
            try {
                value = readMethod.invoke(this);
                Method writeMethod = pd.getWriteMethod();
                Class clazz = pd.getPropertyType();
                if(isBaseSerializationClass(clazz)){
                    if(null != value){
                        BaseSerialization baseSerialization = (BaseSerialization)value;
                        baseSerialization.randomData();
                    }
                }
                if(null == value){
                    if(clazz == String.class){
                        writeMethod.invoke(this,RandomUtil.generateLowerString(5));
                    }
                    if(clazz == Integer.class){
                        writeMethod.invoke(this,Integer.parseInt(RandomUtil.generateNum(5)));
                    }
                    if(clazz == Short.class){
                        writeMethod.invoke(this, Short.parseShort(RandomUtil.generateNum(1)));
                    }
                    if(clazz == Date.class){
                        writeMethod.invoke(this,new Date());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean  isBaseSerializationClass (Class<?> clazz){
        Class<?> superclass = clazz.getSuperclass();
        boolean flag = false;
        while (superclass != null) {
            if(superclass.getName().equals("java.lang.Object")){
                break;
            }
            if(superclass == BaseSerialization.class){
                flag = true;
                break;
            }
            superclass = superclass.getSuperclass();
        }
        return flag;
    }


}