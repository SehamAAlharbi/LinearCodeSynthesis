package seham.phd.synthesis.visitors;

import java.io.File;		
import java.io.IOException;
import java.util.ArrayList;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import seham.phd.synthesis.model.Method;

public class MethodVisitor extends VoidVisitorAdapter<Void> {
	
	private static ArrayList<Method> methodDeclarations = new ArrayList<Method>();
	
	@Override
	public void visit(MethodDeclaration md, Void arg) {
			super.visit(md, arg);
			Method method = new Method(md.getName(), md.getAnnotations(), md.getBody());
			methodDeclarations.add(method);
			
//			System.out.println(method.getName());
//			System.out.println(method.getAnnotation());
//			System.out.println(method.getBody());
//			System.out.println(method.isAnnotated());
//			System.out.println(method.isUtilityMethod());
//			System.out.println(method.isDocumentationMethod());
//			System.out.println("-------");

		}
	
		public static ArrayList<Method> locateUtilityMethods() {

			ArrayList<Method> utilityMethods = new ArrayList<Method>();

			for (Method method : methodDeclarations) {
				if (method.isUtilityMethod()) {
					utilityMethods.add(method);
				}
			}

			return utilityMethods;
		}

		public static ArrayList<Method> locateDocumentationMethods() {
			
			ArrayList<Method> documentationMethods = new ArrayList<Method>();
			for (Method method : methodDeclarations) {
				if (method.isDocumentationMethod()) {
					documentationMethods.add(method);
				}
			}

			return documentationMethods;

		}
	
	
	public static void main(String[] args) throws IOException {
		
		final String FILE_PATH ="src/main/java/seham/phd/synthesis/code/concise/JFrameExample.java";
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		VoidVisitor<Void> visitor = new MethodVisitor();
		visitor.visit(cu, null);
		System.out.println(locateUtilityMethods());
		System.out.println(locateDocumentationMethods());
	}

}
