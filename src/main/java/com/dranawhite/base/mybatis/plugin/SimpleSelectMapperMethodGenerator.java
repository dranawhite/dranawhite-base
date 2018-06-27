package com.dranawhite.base.mybatis.plugin;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class SimpleSelectMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

	public SimpleSelectMapperMethodGenerator() {
		super();
	}

	@Override
	public void addInterfaceElements(Interface interfaze) {
		FullyQualifiedJavaType fqjt =
				new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		importedTypes.add(fqjt);
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		returnType.addTypeArgument(fqjt);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(returnType);
		method.setName("selectList");
		//$NON-NLS-1$
		method.addParameter(new Parameter(fqjt, "example"));
		context.getCommentGenerator().addGeneralMethodComment(method,
				introspectedTable);
		addMapperAnnotations(interfaze, method);

		importedTypes.add(returnType);
		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	public void addMapperAnnotations(Interface interfaze, Method method) {
		return;
	}
}
