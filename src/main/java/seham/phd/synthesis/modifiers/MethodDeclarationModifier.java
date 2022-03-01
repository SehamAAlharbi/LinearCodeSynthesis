package seham.phd.synthesis.modifiers;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.ModifierVisitor;

public class MethodDeclarationModifier extends ModifierVisitor<Void> {

	@Override
	public MethodDeclaration visit(MethodDeclaration md, Void arg) {
		
		super.visit(md, arg);
		return md;

	}
	
	public BlockStmt removeNode (BlockStmt bs, int index) {
		
		super.visit(bs, null);
		
		// What if I want to remove a node of a certain type ?
		bs.getStatements().get(index).remove();
		return bs;
		
	}
	
	public MethodDeclaration modify (MethodDeclaration md, MethodDeclaration utilityMethod) {
		
		super.visit(md, null);
		
		removeNode(md.getBody().get(), 0);
		BlockStmt utilityMethodBody = cloneBody(utilityMethod);
		
		System.out.println(utilityMethodBody);
		
		// Iterate over @Param utilityMethod body and embed statements to @Param md body 
		NodeList<Statement> statements =  utilityMethodBody.getStatements();
		statements.stream().forEach(st -> md.getBody().get().addStatement(st));
		
		return md;
		
	}
	
	public int getInsertionPosition () {
		
		int position = 0;
		
		// specify insertion position, using line no?
		
		return position;
	}
	
	
	public BlockStmt cloneBody (MethodDeclaration utilityMethod) {
		
		CloneVisitor visitor = new CloneVisitor();
		
		// The branch of our tree that represents the BlockStmt of a utility method
		BlockStmt body = utilityMethod.getBody().get();
		BlockStmt clonedBody = (BlockStmt) visitor.visit(body, null);
		
		return clonedBody;
	}
}