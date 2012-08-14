<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
    /** Default constructor */
    public ${declarationName}() {
        super();
    }

<#if pojo.needsMinimalConstructor()>
    /** Minimal constructor */
    public ${declarationName}(
        ${c2j.asParameterList(pojo.getPropertyClosureForMinimalConstructor(), jdk5, pojo)}) {
        super(${c2j.asArgumentList(pojo.getPropertyClosureForMinimalConstructor())});        
    }

</#if>
<#if pojo.needsFullConstructor()>
    /** Full constructor */
    public ${declarationName}(
        ${c2j.asParameterList(pojo.getPropertyClosureForFullConstructor(), jdk5, pojo)}) {
        super(${c2j.asArgumentList(pojo.getPropertyClosureForFullConstructor())});        
    }
</#if>