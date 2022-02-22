package seham.phd.synthesis.model;

import java.util.Optional;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;

/**
 * A class representing a {@link JavaParser} {@link MethodDeclaration Node}.
 * Each instantiation of this class will represent a single JavaParser MethodDeclaration node.
 * 
 * @author Seham Alharbi
 **/

public class MethodDeclarationRepresenter {

	private MethodDeclaration RepresentedMethodDeclaration;

	public MethodDeclarationRepresenter(MethodDeclaration RepresentedMethodDeclaration) {

		this.RepresentedMethodDeclaration = RepresentedMethodDeclaration;
	}

	public MethodDeclaration getRepresentedMethodDeclaration() {
		return RepresentedMethodDeclaration;
	}

	public void setRepresentedMethodDeclaration(MethodDeclaration representedMethodDeclaration) {
		RepresentedMethodDeclaration = representedMethodDeclaration;
	}

	public SimpleName getName() {
		return this.RepresentedMethodDeclaration.getName();
	}

	public void setName(SimpleName name) {
		this.RepresentedMethodDeclaration.setName(name);
	}

	public NodeList<AnnotationExpr> getAnnotations() {
		return this.RepresentedMethodDeclaration.getAnnotations();
	}

	public void setAnnotations(AnnotationExpr annotationExpr) {
		this.RepresentedMethodDeclaration.setAnnotation(0, annotationExpr);
	}

	public void setBody(BlockStmt body) {
		this.RepresentedMethodDeclaration.setBody(body);
	}

	public Optional<BlockStmt> getBody() {
		return this.RepresentedMethodDeclaration.getBody();
	}

	public boolean isAnnotated() {

		if (!this.RepresentedMethodDeclaration.getAnnotations().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUtilityMethod() {

		if (this.RepresentedMethodDeclaration.isAnnotationPresent("Utility")) {
			return true;
		} else {
			return false;

		}
	}

	public boolean isDocumentationMethod() {

		if (this.RepresentedMethodDeclaration.isAnnotationPresent("Documentation")) {
			return true;
		} else {
			return false;

		}
	}

	public String toString() {
		return "Name: " + this.RepresentedMethodDeclaration.getNameAsString() + "\nAnnotations: "
				+ this.RepresentedMethodDeclaration.getAnnotations();
	}

}
