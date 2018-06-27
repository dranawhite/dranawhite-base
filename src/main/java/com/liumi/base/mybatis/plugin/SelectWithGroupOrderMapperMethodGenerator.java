package com.liumi.base.mybatis.plugin;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class SelectWithGroupOrderMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		FullyQualifiedJavaType fqjt =
				new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		importedTypes.add(fqjt);
		importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
		importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));

		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		returnType.addTypeArgument(fqjt);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(returnType);
		method.setName("selectWithGroupOrder");

		Parameter example = new Parameter(fqjt, "example");
		example.addAnnotation(
				"@Param(\"" + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() +
						"\")");
		method.addParameter(example);

		Parameter groupBy = new Parameter(FullyQualifiedJavaType.getStringInstance(), "group");
		groupBy.addAnnotation("@Param(\"group\")");
		method.addParameter(groupBy);

		Parameter order = new Parameter(FullyQualifiedJavaType.getStringInstance(), "order");
		order.addAnnotation("@Param(\"order\")");
		method.addParameter(order);

		Parameter desc = new Parameter(FullyQualifiedJavaType.getStringInstance(), "desc");
		desc.addAnnotation("@Param(\"desc\")");
		method.addParameter(desc);

		context.getCommentGenerator().addGeneralMethodComment(method,
				introspectedTable);

		importedTypes.add(returnType);
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}
}
