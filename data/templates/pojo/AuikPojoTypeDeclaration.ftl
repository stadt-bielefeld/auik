<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
${pojo.getClassJavaDoc(
"A class that represents a row in the ${declarationName} database table.
You can customize the behavior of this class by editing the class,
{@link " + pojo.getDeclarationName() + "}.", 0)}
 */
${pojo.getClassModifiers()} abstract ${pojo.getDeclarationType()} Abstract${declarationName} ${pojo.getImplementsDeclaration()}