<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#--  /** default constructor */ -->
    public Abstract${declarationName}() {
    }

<#if pojo.needsMinimalConstructor()>
<#-- /** minimal constructor */ -->
    public Abstract${declarationName}(
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
<#-- /** full constructor */ -->
    public Abstract${declarationName}(
    	${c2j.asParameterList(pojo.getPropertyClosureForFullConstructor(), jdk5, pojo)}) {
<#if pojo.isSubclass() && !pojo.getPropertyClosureForSuperclassFullConstructor().isEmpty()>
        super(${c2j.asArgumentList(pojo.getPropertyClosureForSuperclassFullConstructor())});        
</#if>
<#foreach field in pojo.getPropertiesForFullConstructor()> 
        this.${field.name} = ${field.name};
</#foreach>
    }
</#if>    

