package com.liumi.base.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class SimpleSelectElementGenerator extends AbstractXmlElementGenerator {

	public SimpleSelectElementGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("select");
		parentElement.addElement(answer);

		answer.addAttribute(new Attribute("id", "selectList"));
		answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
		answer.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

		answer.addElement(new TextElement("select"));
		answer.addElement(getBaseColumnListElement());

		StringBuilder sb = new StringBuilder();

		sb.append(" from ");
		sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		//$NON-NLS-1$
		XmlElement dynamicElement = new XmlElement("where");
		answer.addElement(dynamicElement);
		dynamicElement.addElement(new TextElement(" 1=1 "));

		for (IntrospectedColumn introspectedColumn : introspectedTable
				.getNonPrimaryKeyColumns()) {
			//$NON-NLS-1$
			XmlElement isNotNullElement = new XmlElement("if");
			sb.setLength(0);
			sb.append(introspectedColumn.getJavaProperty());
			//$NON-NLS-1$
			sb.append(" != null");
			//$NON-NLS-1$
			isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
			dynamicElement.addElement(isNotNullElement);

			sb.setLength(0);
			sb.append(" and ");
			sb.append(MyBatis3FormattingUtilities
					.getEscapedColumnName(introspectedColumn));
			//$NON-NLS-1$
			sb.append(" = ");
			sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));

			isNotNullElement.addElement(new TextElement(sb.toString()));
		}
	}

}
