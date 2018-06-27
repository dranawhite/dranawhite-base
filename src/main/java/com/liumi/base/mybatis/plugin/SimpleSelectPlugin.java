package com.liumi.base.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.List;
import java.util.Random;

public class SimpleSelectPlugin extends PluginAdapter {

	private FullyQualifiedJavaType persistenceObject;

	public SimpleSelectPlugin() {
		super();
		//$NON-NLS-1$
		persistenceObject = new FullyQualifiedJavaType(PersistenceObject.class.getName());
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement answer = document.getRootElement();
		SimpleSelectElementGenerator elementGenerator = new SimpleSelectElementGenerator();
		elementGenerator.setContext(context);
		elementGenerator.setIntrospectedTable(introspectedTable);
		elementGenerator.addElements(answer);
		return true;
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		AbstractJavaMapperMethodGenerator methodGenerator = new SimpleSelectMapperMethodGenerator();
		methodGenerator.setContext(context);
		methodGenerator.setIntrospectedTable(introspectedTable);
		methodGenerator.addInterfaceElements(interfaze);
		return true;
	}


	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	protected void makeSerializable(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(persistenceObject);
		topLevelClass.setSuperClass(persistenceObject);
		Field field = new Field();
		field.setFinal(true);
		Random r = new Random();
		//$NON-NLS-1$
		field.setInitializationString("" + r.nextInt());
		//$NON-NLS-1$
		field.setName("serialVersionUID");
		field.setStatic(true);
		//$NON-NLS-1$
		field.setType(new FullyQualifiedJavaType("long"));
		field.setVisibility(JavaVisibility.PRIVATE);
		context.getCommentGenerator().addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
	}
}
