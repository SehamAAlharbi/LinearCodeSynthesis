package seham.phd.synthesis.visitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.MethodCallExpr;

import seham.phd.synthesis.model.MethodDeclarationRepresenter;

public class MethodDeclarationVisitor extends VoidVisitorAdapter<Void> {

	private ArrayList<MethodDeclaration> allMethodDeclarations = new ArrayList<MethodDeclaration>();
	private ArrayList<MethodDeclaration> utilityMethods = new ArrayList<MethodDeclaration>();
	private ArrayList<MethodDeclaration> documentationMethods = new ArrayList<MethodDeclaration>();

	
	public void parse(File file) throws FileNotFoundException {

		CompilationUnit cu = StaticJavaParser.parse(file);
		visit(cu, null);
		locateUtilityMethods();
		locateDocumentationMethods ();

	} 
	
	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		allMethodDeclarations.add(md);
	}

	public ArrayList<MethodDeclaration> getUtilityMethods() {

		return this.utilityMethods;
	}

	public ArrayList<MethodDeclaration> getDocumentationMethods() {

		return this.documentationMethods;

	}
	
	public ArrayList<MethodDeclaration> getAllMethodDeclarations () {

		return this.allMethodDeclarations;
	}

	public Map<MethodCallExpr, Integer> locateUtilityCalls(MethodDeclarationRepresenter documentationmethod) {

		// given a doc method, analysis its body, returns a map of each method call and its line number

		return null;
	}
	
	private void locateUtilityMethods() {

		// use streams for better practice
		utilityMethods.addAll(allMethodDeclarations.stream().filter(declaration -> declaration.isAnnotationPresent("Utility")).collect(Collectors.toList()));
//		for (MethodDeclaration declaration : allMethodDeclarations) {
//			if (declaration.isAnnotationPresent("Utility")) {
//				utilityMethods.add(declaration);
//			}
//		}

	}
	
	private void locateDocumentationMethods() {

		// use streams for better practice
		for (MethodDeclaration declaration : allMethodDeclarations) {
			if (declaration.isAnnotationPresent("Documentation")) {
				documentationMethods.add(declaration);
			}
		}
	}
}
