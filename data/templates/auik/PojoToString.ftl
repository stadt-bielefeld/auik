    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return ${pojo.importType("de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString")}.toStringForClass(this); 
    }

<#if pojo.needsToString()>
    /**
     * Get a string representation for the gui
     * @return String
     */
    public String toGuiString() {
<#foreach property in pojo.getToStringPropertiesIterator()>
        return ${pojo.getGetterSignature(property)}();
</#foreach>
    }

</#if>
    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
<#foreach property in pojo.getPropertiesForFullConstructor()>
        buffer.append("${property.getName()}").append("='").append(${pojo.getGetterSignature(property)}()).append("' ");			
</#foreach>
        buffer.append("]");

        return buffer.toString();
    }

