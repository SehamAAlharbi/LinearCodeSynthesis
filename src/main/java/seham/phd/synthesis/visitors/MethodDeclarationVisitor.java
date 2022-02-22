package seham.phd.synthesis.visitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.MethodCallExpr;

import seham.phd.synthesis.model.MethodDeclarationRepresenter;

public class MethodDeclarationVisitor extends VoidVisitorAdapter<Void> {

	private static ArrayList<MethodDeclaration> allMethodDeclarations = new ArrayList<MethodDeclaration>();

	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		allMethodDeclarations.add(md);
	}

	public ArrayList<MethodDeclaration> getUtilityMethods() {

		ArrayList<MethodDeclaration> utilityMethods = new ArrayList<MethodDeclaration>();

		// use streams for better practice
		for (MethodDeclaration declaration : allMethodDeclarations) {
			if (declaration.isAnnotationPresent("Utility")) {
				utilityMethods.add(declaration);
			}
		}

		return utilityMethods;
	}

	public ArrayList<MethodDeclaration> getDocumentationMethods() {

		ArrayList<MethodDeclaration> documentationMethods = new ArrayList<MethodDeclaration>();

		// use streams for better practice
		for (MethodDeclaration declaration : allMethodDeclarations) {
			if (declaration.isAnnotationPresent("Documentation")) {
				documentationMethods.add(declaration);
			}
		}

		return documentationMethods;

	}

	public Map<MethodCallExpr, Integer> locateUtilityCalls(MethodDeclarationRepresenter documentationmethod) {

		// given a doc method, analysis its body, returns a map of each method call and its line number

		return null;
	}

	public void parse(File file) throws FileNotFoundException {

		CompilationUnit cu = StaticJavaParser.parse(file);
		visit(cu, null);

	}

}
