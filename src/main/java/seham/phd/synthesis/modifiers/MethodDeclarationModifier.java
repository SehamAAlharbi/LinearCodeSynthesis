package seham.phd.synthesis.modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.javaparser.Position;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;	
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import seham.phd.synthesis.visitors.MethodDeclarationVisitor;


public class MethodDeclarationModifier extends ModifierVisitor<Void> {
	
	public MethodDeclarationModifier() {
		
	}

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
	
	public MethodDeclaration modifyAllDocMethods(CompilationUnit cu, MethodDeclarationVisitor visitor) {
		
		// This is what should happen, change all Doc methods in the CU of this visitor
//		visitor.getDocumentationMethods().stream().forEach(method -> modifyMethod(method, visitor));
		
		// here I am just experimenting with one method doc
		MethodDeclaration md = modifyMethod(visitor.getDocumentationMethods().get(0), visitor);
		cu.recalculatePositions();
		return md;
		
	}
	
	private MethodDeclaration modifyMethod (MethodDeclaration md, MethodDeclarationVisitor visitor) {
		
		super.visit(md, null);
		
		
		Map<MethodDeclaration, Integer> utilityCallsMap = visitor.locateUtilityCalls(md);
		BlockStmt newDocMethodBody = new BlockStmt();
		
		utilityCallsMap.keySet().forEach(k -> {
			
			    // clone each utility method body
				BlockStmt utilityMethodBody = cloneBody(k);

				// Iterate over @Param utilityMethod body and embed statements to @Param md body
				NodeList<Statement> statements =  utilityMethodBody.getStatements();
				List <Integer> nodesToRemove = new ArrayList <Integer> ();
				
				md.getBody().get().getChildNodes().forEach(node -> {
					
					if (node.getRange().get().begin.line == utilityCallsMap.get(k)) {
						
						nodesToRemove.add(md.getBody().get().getChildNodes().indexOf(node));
					}
					
				
				});
				
//				 iterate to remove and add
				nodesToRemove.stream().forEach(index -> {
					md.getBody().get().getStatements().get(index).remove();
//					statements.stream().forEach(st -> md.getBody().get().getStatements().add(index, st));
				});
				
				
				
//				md.getBody().get().findAll(MethodCallExpr.class).stream().forEach(mce -> {
//					
//					if (mce.getNameAsString().equalsIgnoreCase(k.getNameAsString())) {
//						statements.stream().forEach(st -> newDocMethodBody.addStatement(st));
//					}
//				});

		});
		
//		md.replace(md.getBody().get(), newDocMethodBody);
		
		return md;
		
	}
	
	public int getInsertionPosition (int lineNumber) {
		
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