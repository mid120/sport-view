package com.xforce.cn.sportview.config;


import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafConfig {
    @Autowired
    ApplicationContext applicationContext;

    @Value("${view.dir}")
    String view_dir ;


    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // SpringResourceTemplateResolver自动与Spring自己集成
        // 资源解决基础设施, 强烈推荐。
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
//       templateResolver.setPrefix("classpath:/templates/");
//       外置配置文件
        templateResolver.setPrefix("file:"+view_dir);

//      URL resource = this.getClass().getClassLoader().getResource("templates/");
//      String devResource = resource.getFile().toString().replaceAll("target/classes", "src/main/resources");
//      templateResolver.setPrefix("file:"+devResource);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        // HTML是默认值, 为了清楚起见, 在此处添加。
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 默认情况下, 模板缓存为true。如果您想要设置为false
        // 模板在修改时自动更新。
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        // SpringTemplateEngine自动应用SpringStandardDialect
        // 并启用Spring自己的MessageSource消息解析机制。
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // 使用Spring 4.2.4或更高版本启用SpringEL编译器
        // 可以加快大多数情况下的执行速度, 但是当一个模板中
        // 的表达式在不同数据类型之间重用时,
        // 可能与特定情况不兼容, 因此该标志默认为“false”
        // 以实现更安全的向后兼容性。
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }


    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

}
