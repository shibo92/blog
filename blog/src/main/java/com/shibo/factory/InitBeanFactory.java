package com.shibo.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shibo
 * @date 19-12-30 上午10:54
 */
@Component
public class InitBeanFactory implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(bean);
        return bean;
    }
}
