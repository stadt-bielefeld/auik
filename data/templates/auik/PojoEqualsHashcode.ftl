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

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.${pojo.getGetterSignature(clazz.identifierProperty)}() == null ?
            0 : this.${pojo.getGetterSignature(clazz.identifierProperty)}().hashCode();
        result = result * 37 + idValue;
        return result;
    }
    
</#if>
