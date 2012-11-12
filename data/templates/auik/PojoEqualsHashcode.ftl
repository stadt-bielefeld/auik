<#if clazz.identifierProperty?exists>
    /**
     * @param other
     * @return <code>true</code>, if this and other are equal,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof ${pojo.getDeclarationName()})) return false;
        return (this.${pojo.getGetterSignature(clazz.identifierProperty)}().equals(
            ((${pojo.getDeclarationName()}) other).${pojo.getGetterSignature(clazz.identifierProperty)}()));
    }

</#if>
<#if pojo.needsEqualsHashCode() && !clazz.superclass?exists>
    public int hashCode() {
        int result = 17;
<#foreach property in pojo.getAllPropertiesIterator()>
        ${pojo.generateHashCode(property, "result", "this", jdk5)}
</#foreach>
        return result;
    }
</#if>
