package com.zq.processor;


import com.zq.annoation.DescAnnotation;
import com.zq.utils.FileHelper;

import java.io.IOException;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * description：
 * ===============================
 * creator：zq
 * create time：2020/7/17  10:59 AM
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：2020/7/17  10:59 AM
 */
public class AnnoationDescField {
    private VariableElement mFieldElement;

    private String title;
    private String desc;

    public AnnoationDescField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {//判断是否是类成员
            throw new IllegalArgumentException(String.format("Only field can be annotated with @%s",
                    DescAnnotation.class.getSimpleName()));
        }
        mFieldElement = (VariableElement) element;
        //获取注解和值
        DescAnnotation descAnnotation = mFieldElement.getAnnotation(DescAnnotation.class);
        title = descAnnotation.title();
        desc = descAnnotation.desc();
        try {
            FileHelper.writeTxtFile(title + "," + desc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("title:" + title + ",desc:" + desc);
    }

    public Name getFieldName() {
        return mFieldElement.getSimpleName();
    }

    public String getResId() {
        return title;
    }

    public TypeMirror getFieldType() {
        return mFieldElement.asType();
    }
}
