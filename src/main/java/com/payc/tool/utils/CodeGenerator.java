package com.payc.tool.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自动生产代码工具类
 * </p>
 *
 * @author SunmeSpace
 * @since 2019-07-23
 */

public class CodeGenerator {

    public static void main(String[] args) {
        String tableNames = "monthly_charge";//多个表用逗号隔开
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局策略配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/nutrition";
        // 生成文件的输出目录,默认D根目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        gc.setAuthor("wrb");
        // 是否打开输出目录,默认true
        gc.setOpen(false);
        // 是否在xml中添加二级缓存配置,默认false
        gc.setEnableCache(true);
        // 开启 swagger2 模式,默认false
        //gc.setSwagger2(true);

        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://121.199.166.3:7233/manage?useUnicode=true&characterEncoding=utf8&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("ntest");
        dsc.setPassword("KmE&220#%wh");
//        dsc.setUrl("jdbc:mysql://47.105.157.183:7233/nutrition?useUnicode=true&characterEncoding=utf8&useSSL=false");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("nutrition_user");
//        dsc.setPassword("GHmE&220#%w1");
//        dsc.setUrl("jdbc:mysql://121.199.166.3:7233/yc000?useUnicode=true&characterEncoding=utf8&useSSL=false");
//        //dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("ntest");
//        dsc.setPassword("KmE&220#%wh");
        mpg.setDataSource(dsc);

        // 包名配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("info");
        pc.setParent("com.payc.ecc.nutrition");
        pc.setEntity("model.entity");
        pc.setMapper("dao");
        pc.setXml("mybatis/mappers");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mappers/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // templateConfig.setEntity();
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityClass("com.hhotel.crs.BaseEntity");
        // 自定义基础的Entity类，公共字段
        // strategy.setSuperEntityColumns(new String[] {"id","gmtCreate","gmtModified"});
        // 是否为lombok模型
        strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //表名，多个英文逗号分割,表名出错，文件不生成
        strategy.setInclude(tableNames.split(","));
        // 驼峰转连字符 如 umps_user 变为 upms/user
        //strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
