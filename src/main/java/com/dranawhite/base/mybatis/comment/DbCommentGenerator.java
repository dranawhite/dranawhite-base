package com.dranawhite.base.mybatis.comment;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 根据数据库备注生成实体注释
 * <pre>
 * 配置用法：
 *      suppressDate：               取消生成日期注释，默认值false
 *      suppressAllComments:         取消生成所有注释，默认值false
 *      suppressModelAlias:          取消生成Model类@Alias注解，默认值为true
 *      suppressCopyRight:           取消生成类的CopyRight,默认值为true
 *      addRemarkComments:           添加表备注作为注释，默认值false，若suppressAllComments设置为true，则忽略该字段
 *      author:                      类文件创建者，默认不生成，若suppressAllComments设置为true，则忽略该字段
 *      version:                     类文件版本，默认不生成，若suppressAllComments设置为true，则忽略该字段
 *      email:                       类创建者邮箱，默认不生成，若suppressAllComments设置为true，则忽略该字段
 *      fromYear:                    起始年份，若suppressAllComments设置为true，则忽略该字段
 *      toYear:                      截止年份，若suppressAllComments设置为true，则忽略该字段
 *      company:                     公司名，若suppressAllComments设置为true，则忽略该字段
 * </pre>
 *
 * @author liangyq 2018/1/26
 */
public class DbCommentGenerator implements CommentGenerator {

	/**
	 * properties配置
	 */
	private Properties properties;

	/**
	 * 是否生成日期注释
	 */
	private boolean suppressDate;

	/**
	 * 是否生成所有注释
	 */
	private boolean suppressAllComments;

	/**
	 * 取消生成CopyRight
	 */
	private boolean suppressCopyRight;

	/**
	 * 添加备注作为注释
	 * 若suppressAllComments设置为true，则忽略该字段
	 */
	private boolean addRemarkComments;

	private SimpleDateFormat dateFormat;

	/**
	 * 类文件创建者
	 */
	private String author;

	/**
	 * 类创建者邮箱
	 */
	private String email;

	/**
	 * 类文件版本号
	 */
	private String version;

	/**
	 * 起始年份
	 */
	private String fromYear;

	/**
	 * 截止年份
	 */
	private String toYear;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 是否生成@Alias注解
	 */
	private boolean suppressModelAlias;

	/**
	 * 添加生成@Data注解
	 */
	private boolean addLombokData;

	private VelocityReader reader;

	public DbCommentGenerator() throws IOException {
		super();
		properties = new Properties();
		suppressModelAlias = true;
		suppressCopyRight = true;
		reader = new VelocityReader();
		reader.init();
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		transferEncoding(properties);
		this.properties.putAll(properties);
		suppressDate = isTrue(properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
		suppressAllComments = isTrue(properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
		addRemarkComments = isTrue(properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
		suppressModelAlias = isTrue(properties
				.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_SUPPRESS_MODEL_ALIAS));
		suppressCopyRight = isTrue(properties
				.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_SUPPRESS_COPY_RIGHT));
		addLombokData = isTrue(properties.getProperty(DbPropertyRegistry
				.COMMENT_GENERATOR_ADD_LOMBOK_DATA));
		author = properties.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_AUTHOR);
		email = properties.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_EMAIL);
		version = properties.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_VERSION);
		fromYear = properties.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_FROM_YEAR);
		toYear = properties.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_TO_YEAR);
		company = properties.getProperty(DbPropertyRegistry.COMMENT_GENERATOR_COMPANY);
		String dateFormatString =
				properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
		if (StringUtility.stringHasValue(dateFormatString)) {
			dateFormat = new SimpleDateFormat(dateFormatString);
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		if (introspectedColumn == null) {
			return;
		}

		field.addJavaDocLine("/**");
		String remark = introspectedColumn.getRemarks();
		StringBuilder sb = new StringBuilder(" * ");
		sb.append(remark);
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		addFieldComment(field, introspectedTable, null);
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		if (suppressAllComments || !addRemarkComments) {
			return;
		}

		String remark = introspectedTable.getRemarks();
		reader.setVmPath(VelocityConstants.DEFAULT_CLASS_PATH);
		Writer writer = reader
				.putVariables(assembleClassVariable(remark), VelocityConstants.DEFAULT_CLASS_PATH);
		topLevelClass.addJavaDocLine(writer.toString());
		if (!suppressModelAlias) {
			String annotation = "@Alias(\"" + topLevelClass.getType().getShortName() + "\")";
			topLevelClass.addAnnotation(annotation);
			topLevelClass.addImportedType("org.apache.ibatis.type.Alias");
		}
		if (addLombokData) {
			topLevelClass.addAnnotation("@Data");
			topLevelClass.addImportedType("lombok.Data");
		}
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		// Do Nothing
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable,
			boolean markAsDoNotDelete) {
		// Do Nothing
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		// Do Nothing
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		reader.setVmPath(VelocityConstants.DEFAULT_GETTER_PATH);
		String methodName = method.getName();
		char[] methodNameChs = methodName.toCharArray();
		methodNameChs[3] = (char) (methodNameChs[3] + 32);
		char[] fieldNameChs = Arrays.copyOfRange(methodNameChs, 3, methodNameChs.length);
		Writer writer = reader
				.putVariables(assembleSetOrGetVariable(new String(fieldNameChs)),
						VelocityConstants.DEFAULT_GETTER_PATH);
		method.addJavaDocLine(writer.toString());
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		reader.setVmPath(VelocityConstants.DEFAULT_SETTER_PATH);
		Writer writer = reader
				.putVariables(assembleSetOrGetVariable(method.getParameters().get(0).getName()),
						VelocityConstants.DEFAULT_SETTER_PATH);
		method.addJavaDocLine(writer.toString());
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		// Do Nothing
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		if (suppressAllComments || suppressCopyRight) {
			return;
		}

		reader.setVmPath(VelocityConstants.DEFAULT_COPYRIGHT_PATH);
		Writer writer = reader
				.putVariables(assembleCopyrightVariable(compilationUnit.getType().getShortName())
						, VelocityConstants.DEFAULT_COPYRIGHT_PATH);
		compilationUnit.addFileCommentLine(writer.toString());
	}

	@Override
	public void addComment(XmlElement xmlElement) {
		// Do Nothing
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		// Do Nothing
	}

	private Map<String, String> assembleCopyrightVariable(String fileName) {
		Map<String, String> variableMap = new HashMap<>(16);
		if (author != null && author.length() != 0) {
			variableMap.put("author", author);
		}
		if (email != null && email.length() != 0) {
			variableMap.put("email", email);
		}
		if (version != null && version.length() != 0) {
			variableMap.put("version", version);
		}
		if (company != null && company.length() != 0) {
			variableMap.put("company", company);
		}
		if (fromYear != null && fromYear.length() != 0 && toYear != null && toYear.length() != 0) {
			variableMap.put("fromYear", fromYear);
			variableMap.put("toYear", toYear);
		}
		variableMap.put("fileName", fileName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		variableMap.put("curTime", sdf.format(new Date()));
		return variableMap;
	}

	private Map<String, String> assembleSetOrGetVariable(String fieldName) {
		Map<String, String> variableMap = new HashMap<>(1);
		variableMap.put("param", fieldName);
		return variableMap;
	}

	private Map<String, String> assembleClassVariable(String remark) {
		Map<String, String> variableMap = new HashMap<>(1);
		if (remark != null) {
			variableMap.put("remark", remark);
		}
		if (author != null) {
			variableMap.put("author", author);
		}
		if (email != null) {
			variableMap.put("email", email);
		}
		if (version != null) {
			variableMap.put("version", version);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		variableMap.put("curTime", sdf.format(new Date()));
		return variableMap;
	}

	private void transferEncoding(Properties prop) {
		try {
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				String value = (String) entry.getValue();
				String newValue = new String(value.getBytes(), "UTF-8");
				prop.put(entry.getKey(), newValue);
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}
}
