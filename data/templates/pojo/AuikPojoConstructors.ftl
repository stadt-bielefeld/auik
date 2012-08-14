<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
    /** Default constructor */
    protected Abstract${declarationName}() {
        // This is intentionally left blank.
    }

<#if pojo.needsMinimalConstructor()>
    /** Minimal constructor */
    protected Abstract${declarationName}(
        ${c2j.asParameterList(pojo.getPropertyClosureForMinimalConstructor(), jdk5, pojo)}) {
<#if pojo.isSubclass() && !pojo.getPropertyClosureForSuperclassMinimalConstructor().isEmpty()>
        super(${c2j.asArgumentList(pojo.getPropertyClosureForSuperclassMinimalConstructor())});        
</#if>
<#foreach field in pojo.getPropertiesForMinimalConstructor()>
        this.${field.name} = ${field.name};
</#foreach>
    }
</#if>

<#if pojo.needsFullConstructor()>
    /** Full constructor */
    protected Abstract${declarationName}(
        ${c2j.asParameterList(pojo.getPropertyClosureForFullConstructor(), jdk5, pojo)}) {
<#if pojo.isSubclass() && !pojo.getPropertyClosureForSuperclassFullConstructor().isEmpty()>
        super(${c2j.asArgumentList(pojo.getPropertyClosureForSuperclassFullConstructor())});        
</#if>
<#foreach field in pojo.getPropertiesForFullConstructor()> 
        this.${field.name} = ${field.name};
</#foreach>
    }
</#if>

