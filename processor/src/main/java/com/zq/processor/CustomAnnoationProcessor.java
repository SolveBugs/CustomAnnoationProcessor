package com.zq.processor;


import com.zq.annoation.DescAnnotation;
import com.zq.utils.FileHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;


/**
 * description：
 * ===============================
 * creator：zq
 * create time：2020/7/17  10:58 AM
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：2020/7/17  10:52 AM
 */
public class CustomAnnoationProcessor extends AbstractProcessor {
    /**
     * 元素相关的辅助类
     */
    private Elements mElementUtils;
    /**
     * 日志相关的辅助类
     */
    private Messager mMessager;
    /**
     * 解析的目标注解集合
     */
    private Map<String, AnnotatedClass> mAnnotatedClassMap = new HashMap<>();

    private static final String docOutputPathKey = "docOutputPath";
    private static final String docOutputNameKey = "docOutputName";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        System.out.println("......CustomAnnoationProcessor.init......start");
        super.init(processingEnv);
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        Map<String, String> options = processingEnv.getOptions();
        if (options != null) {
            if (options.containsKey(docOutputPathKey)) {
                FileHelper.path = options.get(docOutputPathKey);
            }
            if (options.containsKey(docOutputNameKey)) {
                FileHelper.name = options.get(docOutputNameKey);
            }
        }
        try {
            FileHelper.creatTxtFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("......CustomAnnoationProcessor.init......end");
    }

    @Override
    public Set getSupportedAnnotationTypes() {
        Set types = new LinkedHashSet<>();
        types.add(DescAnnotation.class.getCanonicalName());//返回该注解处理器支持的注解集合
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set annotations, RoundEnvironment roundEnv) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "开始处理DescAnnotation注解......");
        mAnnotatedClassMap.clear();
        try {
            processAnnoationDesc(roundEnv);
        } catch (IllegalArgumentException e) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            return true;
        }
        return true;
    }

    /**
     * 遍历目标RoundEnviroment
     *
     * @param roundEnv
     */
    private void processAnnoationDesc(RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(DescAnnotation.class)) {
            AnnotatedClass annotatedClass = getAnnotatedClass(element);
            AnnoationDescField field = new AnnoationDescField(element);
            annotatedClass.addField(field);
        }
    }

    /**
     * 如果在map中存在就直接用，不存在就new出来放在map里
     *
     * @param element
     */
    private AnnotatedClass getAnnotatedClass(Element element) {
        TypeElement encloseElement = (TypeElement) element.getEnclosingElement();
        String fullClassName = encloseElement.getQualifiedName().toString();
        AnnotatedClass annotatedClass = (AnnotatedClass) mAnnotatedClassMap.get(fullClassName);
        if (annotatedClass == null) {
            annotatedClass = new AnnotatedClass(encloseElement, mElementUtils);
            mAnnotatedClassMap.put(fullClassName, annotatedClass);
        }
        return annotatedClass;
    }
}