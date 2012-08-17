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