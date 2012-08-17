    /* Primary key, foreign keys (relations) and table columns */
<#foreach field in pojo.getAllPropertiesIterator()><#if pojo.getMetaAttribAsBool(field, "gen-property", true)> <#if pojo.hasMetaAttribute(field, "field-description")>    /**
     ${pojo.getFieldJavaDoc(field, 0)}
     */
</#if>   ${pojo.getFieldModifiers(field)} ${pojo.getJavaTypeName(field, jdk5)} ${field.name}<#if pojo.hasFieldInitializor(field, jdk5)> = ${pojo.getFieldInitialization(field, jdk5)}</#if>;
</#if>
</#foreach>

<#if clazz.identifierProperty?has_content>
    /** Logging */
    private static final ${pojo.importType("de.bielefeld.umweltamt.aui.utils.AuikLogger")} log = ${pojo.importType("de.bielefeld.umweltamt.aui.utils.AuikLogger")}.getLogger();

</#if>
