package com.zq.processor;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * description：
 * ===============================
 * creator：zq
 * create time：2020/7/17  10:58 AM
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：2020/7/17  10:58 AM
 */
public class AnnotatedClass {
    /**
     * 类名
     */
    public TypeElement mClassElement;
    /**
     * 成员变量集合
     */
    public List<AnnoationDescField> mFiled;
    /**
     * 元素辅助类
     */
    public Elements mElementUtils;

    public AnnotatedClass(TypeElement classElement, Elements elementUtils) {
        this.mClassElement = classElement;
        this.mElementUtils = elementUtils;
        this.mFiled = new ArrayList<AnnoationDescField>();
    }

    /**
     * 获取当前这个类的全名
     */
    public String getFullClassName() {
        return mClassElement.getQualifiedName().toString();
    }

    /**
     * 添加一个成员
     */
    public void addField(AnnoationDescField field) {
        mFiled.add(field);
    }


    /**
     * 包名
     */
    public String getPackageName(TypeElement type) {
        return mElementUtils.getPackageOf(type).getQualifiedName().toString();
    }

    /**
     * 类名
     */
    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', ' ' );
    }
}
