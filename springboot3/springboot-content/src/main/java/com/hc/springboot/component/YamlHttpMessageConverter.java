package com.hc.springboot.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.hc.springboot.bean.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MimeType;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 这是一个消息转换器 专门用于处理yaml格式的数据
 * 所有消息转换器都需要继承AbstractHttpMessageConverter 或者实现HttpMessageConverter接口 并实现或重写相关方法
 */
public class YamlHttpMessageConverter extends AbstractHttpMessageConverter {
    //对象映射器
    //disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER) 这个是用于禁用yaml文件的头部
    private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));

    /**
     * 非常重要的一个步骤 要将媒体类型 text/yaml 和消息转换器进行绑定
     * 这样SpringMVC就会知道当前的请求是用的什么媒体类型 然后就会调用对应的消息转换器进行处理
     * 这里采用的是构造器的方法
     */
    public YamlHttpMessageConverter(){
        super(new MediaType("text","yaml", Charset.forName("UTF-8")));
    }

    // 这个方法是指定此信息转换器只适合哪些类型对象
    @Override
    protected boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    //这个是将yaml格式的字符串转换成Java对象
    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    //这个是将Java对象转换成yaml格式的字符串
    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        objectMapper.writeValue(outputMessage.getBody(),o);
    }
}
