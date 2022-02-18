package seham.phd.synthesis.visitors;

import java.io.File;	
import java.io.IOException;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import javassist.compiler.ast.Visitor;
import seham.phd.synthesis.model.Method;

public class MethodVisitor extends VoidVisitorAdapter<Void> {
	
	@Override
	public void visit(MethodDeclaration md, Void arg) {
			super.visit(md, arg);
			System.out.println("Method Name Printed: " + md.getNameAsString());
			System.out.println("Method Name Printed: " + md.getAnnotations());
			Method method = new Method(md.getName(), md.getAnnotations(), md.getBody());
			
			System.out.println(method.getName());
			System.out.println(method.getAnnotation());
//			System.out.println(method.getBody());
			System.out.println(method.isAnnotated());
			System.out.println(method.isUtilityMethod());
			System.out.println(method.isDocumentationMethod());
			
			
			System.out.println("-------");
//			if (md.getName().asString().equals("pattern")) {
//				List<CommentNode> comments = md.getAllContainedComments().stream()
//						.map(p -> new CommentNode(p.getClass().getSimpleName(), p.getContent(),
//								p.getRange().map(r -> r.begin.line).orElse(-1), !p.getCommentedNode().isPresent()))
//						.collect(Collectors.toList());
//
//				for (CommentNode comment : comments) {
//					System.out.println(comment.toString());
//				}
//			}
		}
	
	
	public static void main(String[] args) throws IOException {
		
		final String FILE_PATH ="src/main/java/seham/phd/synthesis/code/concise/JFrameExample.java";
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		VoidVisitor<Void> visitor = new MethodVisitor();
		visitor.visit(cu, null);
	}

}
