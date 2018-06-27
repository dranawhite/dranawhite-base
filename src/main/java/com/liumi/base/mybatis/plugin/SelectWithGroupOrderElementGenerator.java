package com.liumi.base.mybatis.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class SelectWithGroupOrderElementGenerator extends AbstractXmlElementGenerator {

	private static final String SELECT_WITH_GROUP_ORDER = "selectWithGroupOrder";
	private static final String BASE_WHERE = "Base_Where";

	public SelectWithGroupOrderElementGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {
		parentElement.addElement(createSelectClause());
		parentElement.addElement(createBaseWhere());
	}

	private XmlElement createSelectClause() {
		String entity_name = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
		XmlElement answer = new XmlElement("select");
		answer.addAttribute(new Attribute("id", SELECT_WITH_GROUP_ORDER));
		answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
		answer.addElement(new TextElement("select"));

		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
		answer.addElement(getBaseColumnListElement());
		answer.addElement(new TextElement(
				" from " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));

		StringBuilder sb = new StringBuilder();
		XmlElement where = new XmlElement("if");
		sb.append(entity_name);
		sb.append(" != null");
		where.addAttribute(new Attribute("test", sb.toString()));
		XmlElement ref = new XmlElement("include");
		ref.addAttribute(new Attribute("refid", "Base_Where"));
		where.addElement(ref);
		answer.addElement(where);

		XmlElement group = new XmlElement("if");
		group.addAttribute(new Attribute("test", "group != null"));
		group.addElement(new TextElement("group by ${group}"));
		answer.addElement(group);

		XmlElement order = new XmlElement("if");
		order.addAttribute(new Attribute("test", "order != null"));
		order.addElement(new TextElement("order by ${order}"));
		answer.addElement(order);

		XmlElement desc = new XmlElement("if");
		desc.addAttribute(new Attribute("test", "desc != null"));
		desc.addElement(new TextElement("${desc}"));
		answer.addElement(desc);
		return answer;
	}

	private XmlElement createBaseWhere() {
		String entity_name = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
		XmlElement answer = new XmlElement("sql");
		answer.addAttribute(new Attribute("id", BASE_WHERE));

		XmlElement dynamicElement = new XmlElement("where");
		answer.addElement(dynamicElement);

		StringBuilder sb = new StringBuilder();
		dynamicElement.addElement(new TextElement(" 1=1 "));

		for (IntrospectedColumn introspectedColumn : introspectedTable
				.getNonPrimaryKeyColumns()) {
			XmlElement isNotNullElement = new XmlElement("if");
			sb.setLength(0);
			sb.append(entity_name);
			sb.append(".");
			sb.append(introspectedColumn.getJavaProperty());
			sb.append(" != null");
			isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
			dynamicElement.addElement(isNotNullElement);

			sb.setLength(0);
			sb.append(" and ");
			sb.append(MyBatis3FormattingUtilities
					.getEscapedColumnName(introspectedColumn));
			sb.append(" = ");
			sb.append(MyBatis3FormattingUtilities
					.getParameterClause(introspectedColumn, entity_name + "."));

			isNotNullElement.addElement(new TextElement(sb.toString()));
		}
		return answer;
	}

	private String getParameterClause(
			IntrospectedColumn introspectedColumn, String prefix) {
		StringBuilder sb = new StringBuilder();

		sb.append("#{");
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		sb.append(".");
		sb.append(introspectedColumn.getJavaProperty(prefix));
		sb.append(",jdbcType=");
		sb.append(introspectedColumn.getJdbcTypeName());

		if (stringHasValue(introspectedColumn.getTypeHandler())) {
			sb.append(",typeHandler=");
			sb.append(introspectedColumn.getTypeHandler());
		}

		sb.append('}');
		return sb.toString();
	}
}
