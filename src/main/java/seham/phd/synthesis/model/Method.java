package seham.phd.synthesis.model;

import java.util.Optional;	

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;

public class Method {

	private SimpleName name;
	private NodeList<AnnotationExpr> annotations;
	private Optional<BlockStmt> body;

	public Method() {

	}

	public Method(SimpleName simpleName, NodeList<AnnotationExpr> nodeList, Optional<BlockStmt> optional) {

		this.name = simpleName;
		this.annotations = nodeList;
		this.body = optional;
	}

	public SimpleName getName() {
		return name;
	}

	public void setName(SimpleName name) {
		this.name = name;
	}

	public NodeList<AnnotationExpr> getAnnotation() {
		return annotations;
	}

	public void setAnnotation(NodeList<AnnotationExpr> annotation) {
		this.annotations = annotation;
	}

	public Optional<BlockStmt> getBody() {
		return body;
	}

	public void setBody(Optional<BlockStmt> body) {
		this.body = body;
	}

	public boolean isAnnotated() {
		if (this.annotations.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isUtilityMethod() {

		boolean isFound = false;
		for (AnnotationExpr expression : this.annotations) {
			if (expression.getName().toString().equalsIgnoreCase("Utility")) {
				isFound = true;
			}
		}
		return isFound;
	}

	public boolean isDocumentationMethod() {
		
		boolean isFound = false;
		for (AnnotationExpr expression : this.annotations) {
			if (expression.getName().toString().equalsIgnoreCase("Documentation")) {
				isFound = true;
			}
		}
		return isFound;
	}

}
