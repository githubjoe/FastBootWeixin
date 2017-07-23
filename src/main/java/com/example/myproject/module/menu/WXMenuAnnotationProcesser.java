package com.example.myproject.module.menu;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.example.myproject.annotation.WXMenu;

@Component
public class WXMenuAnnotationProcesser implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> targetClass = AopUtils.getTargetClass(bean);
		ReflectionUtils.doWithMethods(targetClass, method -> {
			WXMenu wxMenu = AnnotationUtils.getAnnotation(method, WXMenu.class);
			if (wxMenu != null) {
				WXMenuManager.getInstance().add(wxMenu);
			}
		});
		return bean;
	}

}