package seham.phd.synthesis.modifiers;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;

public class MethodDeclarationModifier extends ModifierVisitor<Void> {

	@Override
	public MethodDeclaration visit(MethodDeclaration md, Void arg) {
		return md;

	}
	
	
	public Visitable cloneBody (MethodDeclaration utilityMethod) {
		
		CloneVisitor visitor = new CloneVisitor();
		BlockStmt body = utilityMethod.getBody().get();
		Visitable clonedBody = visitor.visit(body, null);
		
		return clonedBody;
	}
}
