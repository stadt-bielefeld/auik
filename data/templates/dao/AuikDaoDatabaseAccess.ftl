<#if clazz.identifierProperty?has_content>
    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>${declarationName}</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static ${declarationName} merge(${declarationName} detachedInstance) {
        return (${declarationName}) new ${pojo.importType("de.bielefeld.umweltamt.aui.utils.DatabaseAccess")}().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        return ${declarationName}.merge(this).equals(this);
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(${declarationName} detachedInstance) {
        return new ${pojo.importType("de.bielefeld.umweltamt.aui.utils.DatabaseAccess")}().delete(detachedInstance);
    }
    
    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return ${declarationName}.delete(this);
    }
    
    /**
     * Find an <code>${declarationName}</code> instance by its primary key
     * @param id the primary key value
     * @return <code>${declarationName}</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static ${declarationName} findById(${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("getting ${declarationName} instance with id: " + id);
        ${declarationName} instance = (${declarationName})
            new ${pojo.importType("de.bielefeld.umweltamt.aui.utils.DatabaseAccess")}().get(${declarationName}.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    /**
     * Get a list of all <code>${declarationName}</code>
     * @return <code>List<${declarationName}></code> all <code>${declarationName}</code>
     */
    public static ${pojo.importType("java.util.List")}<${declarationName}> getAll() {
        String query = "FROM ${declarationName}";
        ${pojo.importType("java.util.List")}<?> objectList = new ${pojo.importType("de.bielefeld.umweltamt.aui.utils.DatabaseAccess")}().createQuery(query).list(); 
        ${pojo.importType("java.util.List")}<${declarationName}> resultList = new ${pojo.importType("java.util.ArrayList")}<${declarationName}>();
        ${declarationName} result = null;
        for (Object object : objectList) {
            result = (${declarationName}) object;
            resultList.add(result); 
        }
        return resultList;
    }

</#if>
<#foreach queryName in cfg.namedQueries.keySet()>
<#if queryName.startsWith(clazz.entityName + ".")>
<#assign methname = c2j.unqualify(queryName)>
<#assign params = cfg.namedQueries.get(queryName).parameterTypes><#assign argList = c2j.asFinderArgumentList(params, pojo)>
<#if jdk5 && methname.startsWith("find")>
    public ${pojo.importType("java.util.List")}<${declarationName}> ${methname}(${argList}) {
<#elseif methname.startsWith("count")>
    public int ${methname}(${argList}) {
<#else>
    public ${pojo.importType("java.util.List")} ${methname}(${argList}) {
</#if>
        ${pojo.importType("org.hibernate.Query")} query = sessionFactory.getCurrentSession()
                .getNamedQuery("${queryName}");
<#foreach param in params.keySet()>
<#if param.equals("maxResults")>
        query.setMaxResults(maxResults);
<#elseif param.equals("firstResult")>
        query.setFirstResult(firstResult);
<#else>
        query.setParameter("${param}", ${param});
</#if>
</#foreach>
<#if jdk5 && methname.startsWith("find")>
        return (List<${declarationName}>) query.list();
<#elseif methname.startsWith("count")>
        return ( (Integer) query.uniqueResult() ).intValue();
<#else>
        return query.list();
</#if>
    }
</#if>
</#foreach>