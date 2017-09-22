package org.vinci.freemarkerlearn.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XizeTian on 2017/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Lab {
    @Autowired
    Configuration        configuration;
    @Autowired
    FreeMarkerProperties freeMarkerProperties;

    @Test
    public void main() throws IOException, TemplateException {
        Map<String, Object> sharedVariables = new HashMap<>();
        sharedVariables.put("version", "1.0.0-1");

        configuration.setSharedVaribles(sharedVariables);
        Map<String, Object> map      = new HashMap<>();
        Template            template = configuration.getTemplate("domain" + freeMarkerProperties.getSuffix());
        StringWriter        writer   = new StringWriter();
        try {
            template.process(map, writer);
        } finally {
            writer.close();
        }
        System.out.println(writer.getBuffer().toString());
    }
}
