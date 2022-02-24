package seham.phd.synthesis.visitors;

import java.io.File;	
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.MethodCallExpr;


public class MethodDeclarationVisitor extends VoidVisitorAdapter<Void> {

	private ArrayList<MethodDeclaration> allMethodDeclarations = new ArrayList<MethodDeclaration>();
	private ArrayList<MethodDeclaration> utilityMethods = new ArrayList<MethodDeclaration>();
	private ArrayList<MethodDeclaration> documentationMethods = new ArrayList<MethodDeclaration>();

	public CompilationUnit parse(File file) throws FileNotFoundException {

		CompilationUnit cu = StaticJavaParser.parse(file);
	
		return cu;

	}

	@Override
	public void visit(MethodDeclaration md, Void arg) {
		
		super.visit(md, arg);
		allMethodDeclarations.add(md);
		
	}
	
	public ArrayList<MethodDeclaration> getAllMethodDeclarations() {

		return this.allMethodDeclarations;
	}

	public ArrayList<MethodDeclaration> getUtilityMethods() {

		return this.utilityMethods;
	}

	public ArrayList<MethodDeclaration> getDocumentationMethods() {

		return this.documentationMethods;

	}


	/**
	 * Given a documentation method, it analysis its body and look for any utility MethodCallExpr
	 * @param documentationMethod to be analysed
	 * @return a map of each utility MethodCallExpr and its line number
	 * 
	 */
	public Map<MethodCallExpr, Integer> locateUtilityCalls(MethodDeclaration documentationMethod) {

		Map<MethodCallExpr, Integer> utilityCalls = new HashMap<MethodCallExpr, Integer>();

		documentationMethod.findAll(MethodCallExpr.class).forEach(mce -> {
			if (isUtilityMethodCall(mce)) {
				utilityCalls.put(mce, mce.getName().getBegin().get().line);
			}
		});

		return utilityCalls;
	}

	public void locateUtilityMethods() {

		utilityMethods.addAll(allMethodDeclarations.stream()
				.filter(declaration -> declaration.isAnnotationPresent("Utility")).collect(Collectors.toList()));

	}

	public void locateDocumentationMethods() {

		documentationMethods.addAll(allMethodDeclarations.stream()
				.filter(declaration -> declaration.isAnnotationPresent("Documentation")).collect(Collectors.toList()));

	}

	private boolean isUtilityMethodCall(MethodCallExpr methodCall) {

		MethodDeclaration isFound = utilityMethods.stream()
				.filter(md -> md.getNameAsString().equals(methodCall.getNameAsString())).findAny().orElse(null);
		if (isFound != null) {
			return true;
		}

		return false;
	}

	private boolean isDocumentationMethodCall(MethodCallExpr methodCall) {

		MethodDeclaration isFound = documentationMethods.stream()
				.filter(md -> md.getNameAsString().equals(methodCall.getNameAsString())).findAny().orElse(null);
		if (isFound != null) {
			return true;
		}

		return false;
	}
}
