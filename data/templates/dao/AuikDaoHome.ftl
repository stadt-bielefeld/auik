<#include "AuikCopyright.ftl"/>

${pojo.getPackageDeclaration()}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
 * A class that represents a row in the ${declarationName} table.<br>
 * This class may be customized - see mark below.
 */
public class ${declarationName} extends Abstract${declarationName} {

<#if clazz.identifierProperty?has_content>
    /** Logging */
    private static final ${pojo.importType("de.bielefeld.umweltamt.aui.utils.AuikLogger")} log = ${pojo.importType("de.bielefeld.umweltamt.aui.utils.AuikLogger")}.getLogger();

</#if>
<#include "AuikDaoConstructors.ftl"/>

<#include "AuikDaoDatabaseAccess.ftl"/>
    /* Custom code goes below here! */

<#if false>
    // TODO: Fix this! This is generated - customize!
    // TODO: Add some "_" into the return type!
    public ${declarationName} toServiceType() {
        // TODO: Add some "_" into the type!
        ${declarationName} serviceInstance = new ${declarationName}(
            // TODO: Resort the fields to fit the service class!
            // TODO: Change the first character of each field to upper case!
<#foreach field in pojo.getPropertiesForFullConstructor()> 
            this.get${field.name}(),
</#foreach>
            // TODO: Remove the stupid last ","!
        );
        return serviceInstance;
    }

</#if>
}
</#assign>
${pojo.generateImports()}
${classbody}