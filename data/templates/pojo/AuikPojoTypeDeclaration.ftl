<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
${pojo.getClassJavaDoc(pojo.getDeclarationName() + " generated by hbm2java", 0)}
 */
<#include "Ejb3TypeDeclaration.ftl"/>
${pojo.getClassModifiers()} abstract ${pojo.getDeclarationType()} Abstract${declarationName} ${pojo.getExtendsDeclaration()} ${pojo.getImplementsDeclaration()}